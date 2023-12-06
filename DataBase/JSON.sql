--Import/export in JSON format
CREATE OR REPLACE DIRECTORY MY_DIRECTORY AS 'C:\JSON';
CREATE OR REPLACE PROCEDURE ExportUsersReviewsToJson(
    p_directory VARCHAR2,
    p_file_name VARCHAR2
) AS
  file_handle UTL_FILE.FILE_TYPE;
  cursor_data SYS_REFCURSOR;
  json_data CLOB;
BEGIN
  file_handle := UTL_FILE.FOPEN(p_directory, p_file_name, 'W');
  
  OPEN cursor_data FOR
    SELECT JSON_OBJECT(
             'USERS_REVIEWS_ON_MOVIE_ID' VALUE USERS_REVIEWS_ON_MOVIE_ID,
             'USER_REVIEW_TEXT' VALUE USER_REVIEW_TEXT,
             'DATE_OF_REVIEW' VALUE TO_CHAR(DATE_OF_REVIEW, 'YYYY-MM-DD'),
             'USER_PROFILE_ID' VALUE USER_PROFILE_ID,
             'ALL_INFORMATION_ABOUT_FILM_ID' VALUE ALL_INFORMATION_ABOUT_FILM_ID
           ) AS json_object
    FROM USERS_REVIEWS_ON_MOVIE;
  
  LOOP
    FETCH cursor_data INTO json_data;
    EXIT WHEN cursor_data%NOTFOUND;
    UTL_FILE.PUT_LINE(file_handle, json_data);
  END LOOP;
  
  CLOSE cursor_data;
  UTL_FILE.FCLOSE(file_handle);
  
  DBMS_OUTPUT.PUT_LINE('Data exported successfully.');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION IMPORT_DATA_FROM_JSON_TO_USERS_REVIEWS(
    p_directory VARCHAR2,
    p_file_name VARCHAR2
) RETURN NUMBER IS
   json_data CLOB;
   file_handle UTL_FILE.FILE_TYPE;
   total_rows NUMBER := 0;
BEGIN
   -- �������� ����� ��� ������
   file_handle := UTL_FILE.FOPEN(p_directory, p_file_name, 'R');
   
   -- ��������� ������ ������ � �����
   LOOP
      -- ������ ������ �� �����
      BEGIN
         UTL_FILE.GET_LINE(file_handle, json_data);
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            -- ����� �� �����, ���� ��������� ����� �����
            EXIT;
      END;

      -- ������� ������ �� JSON � �������
      INSERT INTO USERS_REVIEWS_ON_MOVIE (
          USERS_REVIEWS_ON_MOVIE_ID,
          USER_REVIEW_TEXT,
          DATE_OF_REVIEW,
          USER_PROFILE_ID,
          ALL_INFORMATION_ABOUT_FILM_ID
      )
      SELECT
          jt.USERS_REVIEWS_ON_MOVIE_ID,
          jt.USER_REVIEW_TEXT,
          TO_DATE(jt.DATE_OF_REVIEW, 'YYYY-MM-DD') AS DATE_OF_REVIEW,
          jt.USER_PROFILE_ID,
          jt.ALL_INFORMATION_ABOUT_FILM_ID
      FROM
          JSON_TABLE(json_data, '$'
              COLUMNS (
                  USERS_REVIEWS_ON_MOVIE_ID NUMBER PATH '$.USERS_REVIEWS_ON_MOVIE_ID',
                  USER_REVIEW_TEXT VARCHAR2(1000) PATH '$.USER_REVIEW_TEXT',
                  DATE_OF_REVIEW VARCHAR2(10) PATH '$.DATE_OF_REVIEW',
                  USER_PROFILE_ID NUMBER PATH '$.USER_PROFILE_ID',
                  ALL_INFORMATION_ABOUT_FILM_ID NUMBER PATH '$.ALL_INFORMATION_ABOUT_FILM_ID'
              )
          ) jt;
      
      -- ���������� �������� �����
      total_rows := total_rows + 1;
   END LOOP;

   -- �������� �����
   UTL_FILE.FCLOSE(file_handle);
   
   -- �������� ����������
   COMMIT;

   -- ����������� ������ ����� ����������� �����
   RETURN total_rows;
EXCEPTION
   -- ��������� ����������
   WHEN OTHERS THEN
      -- ����� ���������� � ������ ������
      ROLLBACK;
      -- ��������� ����������� ����������
      RAISE;
END;
/



BEGIN
  ExportUsersReviewsToJson('MY_DIRECTORY', 'users_reviews_data.json');
END;
/

DECLARE
   rows_inserted NUMBER;
BEGIN
    rows_inserted := IMPORT_DATA_FROM_JSON_TO_USERS_REVIEWS('MY_DIRECTORY', 'users_reviews_data.json');
   DBMS_OUTPUT.PUT_LINE('Total rows inserted: ' || rows_inserted);
END;
/
--SELECT * FROM USERS_REVIEWS_ON_MOVIE;
--DROP TABLE USERS_REVIEWS_ON_MOVIE PURGE;


