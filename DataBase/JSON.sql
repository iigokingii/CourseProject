--Import/export in JSON format
CREATE OR REPLACE DIRECTORY MY_DIRECTORY AS 'C:\JSON';
CREATE OR REPLACE PROCEDURE ExportVisitsTableToJson(
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
             'VISITS_TABLE_ID' VALUE VISITS_TABLE_ID,
             'DATE_OF_VISITS' VALUE TO_CHAR(DATE_OF_VISITS, 'YYYY-MM-DD'),
             'VISITS_NUMBER' VALUE VISITS_NUMBER
           ) AS json_object
    FROM VISITS_TABLE;
  
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

CREATE OR REPLACE FUNCTION ImportDataFromJsonToVisitsTable(
    p_directory VARCHAR2,
    p_file_name VARCHAR2
) RETURN NUMBER IS
   json_data CLOB;
   file_handle UTL_FILE.FILE_TYPE;
   total_rows NUMBER := 0;
BEGIN
   file_handle := UTL_FILE.FOPEN(p_directory, p_file_name, 'R');
   
   LOOP
      BEGIN
         UTL_FILE.GET_LINE(file_handle, json_data);
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            EXIT;
      END;

      INSERT INTO VISITS_TABLE (
          VISITS_TABLE_ID,
          DATE_OF_VISITS,
          VISITS_NUMBER
      )
      SELECT
          jt.VISITS_TABLE_ID,
          TO_DATE(jt.DATE_OF_VISITS, 'YYYY-MM-DD') AS DATE_OF_VISITS,
          jt.VISITS_NUMBER
      FROM
          JSON_TABLE(json_data, '$'
              COLUMNS (
                  VISITS_TABLE_ID NUMBER PATH '$.VISITS_TABLE_ID',
                  DATE_OF_VISITS VARCHAR2(10) PATH '$.DATE_OF_VISITS',
                  VISITS_NUMBER NUMBER PATH '$.VISITS_NUMBER'
              )
          ) jt;
      
      total_rows := total_rows + 1;
   END LOOP;

   UTL_FILE.FCLOSE(file_handle);
   COMMIT;

   RETURN total_rows;
EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
END;
/

SELECT * FROM VISITS_TABLE;

BEGIN
  ExportVisitsTableToJson('MY_DIRECTORY', 'visits_data.json');
END;
/
------------------------------------------------------
ALTER TRIGGER TR_VISITS_TABLE DISABLE;
DECLARE
   rows_inserted NUMBER;
BEGIN
    rows_inserted := ImportDataFromJsonToVisitsTable('MY_DIRECTORY', 'visits_data.json');
   DBMS_OUTPUT.PUT_LINE('Total rows inserted: ' || rows_inserted);
END;
/
ALTER TRIGGER TR_VISITS_TABLE ENABLE;
-------------------------------------------------------
COMMIT;
--SELECT * FROM VISITS_TABLE;
--DROP TABLE VISITS_TABLE PURGE;


