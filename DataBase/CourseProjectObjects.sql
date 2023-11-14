------------------------------------------------
CREATE TABLE USER_ROLE(
    USER_ROLE_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ROLE varchar(100)
) TABLESPACE CP_TS;

DROP TABLE USER_ROLE PURGE;

INSERT INTO USER_ROLE VALUES(DEFAULT,'ADMIN');
INSERT INTO USER_ROLE VALUES(DEFAULT,'USER');
SELECT * FROM USER_ROLE;

------------------------------------------------
--CREATE TABLE USER_PROFILE(
--    USER_PROFILE_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--    LOGIN varchar2(100),
--    EMAIL varchar2(100),
--    AVATAR varchar2(200),
--    PASSWORD varchar2(50),
--    USER_ROLE INT,
--    CONSTRAINT USER_ROLE_CONSTRAINT FOREIGN KEY (USER_ROLE) REFERENCES USER_ROLE(USER_ROLE_ID)
--) TABLESPACE CP_TS;
CREATE TABLE USER_PROFILE(
    USER_PROFILE_ID number GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FIRSTNAME varchar(100),
    SECONDNAME varchar(100),
    EMAIL varchar(200),
    PASSWORD varchar(100),
    USER_ROLE varchar(100),
    CONSTRAINT CONSTRAIN_USER_ROLE CHECK(USER_ROLE IN('ROLE_USER','ROLE_ADMIN'))
)TABLESPACE CP_TS;
select * from USER_PROFILE;

DROP TABLE USER_PROFILE PURGE;
INSERT INTO USER_PROFILE VALUES(DEFAULT,'SS','SSSS','SS@YANDEX.RU','WW','ROLE_USER');
SELECT *FROM USER_PROFILE;

CREATE VIEW FULL_INFORMATION_ABOUT_USER AS 
    SELECT *FROM USER_PROFILE 
        INNER JOIN USER_ROLE
            ON USER_PROFILE.USER_ROLE=USER_ROLE.USER_ROLE_ID
            
------------------------------------------------
CREATE TABLE USERS_REVIEWS_ON_MOVIE(
    USERS_REVIEWS_ON_MOVIE_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    USER_REVIEW_TEXT VARCHAR2(1000),
    DATE_OF_REVIEW DATE,
    USER_PROFILE_ID INT,
    CONSTRAINT USER_PROFILE_ID_CONSTRAINT FOREIGN KEY (USER_PROFILE_ID) REFERENCES USER_PROFILE(USER_PROFILE_ID)
)TABLESPACE CP_TS;
------------------------------------------------
CREATE TABLE FRAME_FROM_MOVIE(
    FRAME_FROM_MOVIE_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FRAME_FROM_MOVIE BLOB NOT NULL,
    FILM_ID INT,
    CONSTRAINT FILM_ID_CONSTRAINT FOREIGN KEY (FILM_ID) REFERENCES ALL_INFORMATION_ABOUT_FILM(ALL_INFORMATION_ABOUT_FILM_ID)
)TABLESPACE CP_TS;
------------------------------------------------
CREATE TABLE ALL_INFORMATION_ABOUT_FILM(
    ALL_INFORMATION_ABOUT_FILM_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TITLE VARCHAR2(100),
    ORIGINAL_TITLE VARCHAR2(100),
    POSTER VARCHAR2(20),--BLOB
    YEAR_OF_POSTING VARCHAR(20),--DATE
    COUNTRY NVARCHAR2(50),
    DESCRIPTION NVARCHAR2(500),
    RATING_IMDb FLOAT,
    RATING_KP FLOAT,
    BOX_OFFICE_RECEIPTS FLOAT,
    BUDGET FLOAT,
    AGE INT,
    FRAME INT,
    VIEWING_TIME VARCHAR2(30),
    GENRES GENRE_TYPE_INSTANCE,
    DIRECTORS DIRECTOR_TYPE_INSTANCE,
    ACTORS ACTOR_TYPE_INSTANCE,
    INTERESTING_FACT INTERESTING_FACT_TYPE_INSTANCE,
    CONSTRAINT FRAME_CONSTRAINT FOREIGN KEY (FRAME) REFERENCES FRAME_FROM_MOVIE(FRAME_FROM_MOVIE_ID)
    )NESTED TABLE GENRES STORE AS GENRES_NT
     NESTED TABLE DIRECTORS STORE AS DIRECTORS_NT
     NESTED TABLE ACTORS STORE AS ACTORS_NT
     NESTED TABLE INTERESTING_FACT STORE AS INTERESTING_FACT_NT(TABLESPACE CP_TS);
    --
INSERT INTO ALL_INFORMATION_ABOUT_FILM VALUES(DEFAULT,'1','1','1','01/11/2023','1','1',2.0,2.4,2222,22,12,'2',
GENRE_TYPE_INSTANCE(GENRE_TYPE_CONSTRUCTOR('BOEVIK'),GENRE_TYPE_CONSTRUCTOR('SSSS')),
DIRECTOR_TYPE_INSTANCE(DIRECTOR_TYPE_CONSTRUCTOR('VALERA'),DIRECTOR_TYPE_CONSTRUCTOR('KIRILL')),
ACTOR_TYPE_INSTANCE(ACTOR_TYPE_CONSTRUCTOR('POLINA'),ACTOR_TYPE_CONSTRUCTOR('DASHA')),
INTERESTING_FACT_TYPE_INSTANCE(INTERESTING_FACT_TYPE_CONSTRUCTOR('XXXXXCCC'),INTERESTING_FACT_TYPE_CONSTRUCTOR('SSSSS'),INTERESTING_FACT_TYPE_CONSTRUCTOR('1111112'))
);
SELECT AL.*,G.*,A.*,D.*,INTR.* 
    FROM ALL_INFORMATION_ABOUT_FILM AL,TABLE(AL.GENRES)G,TABLE(AL.DIRECTORS)D,TABLE(AL.ACTORS)A,TABLE(AL.INTERESTING_FACT)INTR;

DROP TABLE ALL_INFORMATION_ABOUT_FILM;
------------------------------------------------
CREATE TABLE REVIEW(
    REVIEW_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    REVIEW_TEXT VARCHAR2(1000),
    DATE_OF_PUBLISHING VARCHAR2(50),--DATE
    ALL_INFORMATION_ABOUT_FILM INT,
    USER_PROFILE_ID INT,
    CONSTRAINT ALL_INFORMATION_ABOUT_FILM_CONSTRAINT FOREIGN KEY (ALL_INFORMATION_ABOUT_FILM) REFERENCES ALL_INFORMATION_ABOUT_FILM(ALL_INFORMATION_ABOUT_FILM_ID),
    CONSTRAINT USER_PROFILE_ID_CONSTRAINT FOREIGN KEY(USER_PROFILE_ID)REFERENCES USER_PROFILE(USER_PROFILE_ID)
)TABLESPACE CP_TS;
------------------------------------------------
CREATE TABLE USER_WATCH_LATER(
    INT USER_WATCH_LATER_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ALL_INFORMATION_ABOUT_FILM INT,
    USER_PROFILE_ID INT,
    CONSTRAINT ALL_INFORMATION_ABOUT_FILM_CONSTRAINT FOREIGN KEY (ALL_INFORMATION_ABOUT_FILM) REFERENCES ALL_INFORMATION_ABOUT_FILM(ALL_INFORMATION_ABOUT_FILM_ID),
    CONSTRAINT USER_PROFILE_ID_CONSTRAINT FOREIGN KEY(USER_PROFILE_ID)REFERENCES USER_PROFILE(USER_PROFILE_ID)
)TABLESPACE CP_TS;
------------------------------------------------
CREATE TABLE USER_LIKE(
    INT USER_LIKE_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ALL_INFORMATION_ABOUT_FILM INT,
    USER_PROFILE_ID INT,
    CONSTRAINT ALL_INFORMATION_ABOUT_FILM_CONSTRAINT FOREIGN KEY (ALL_INFORMATION_ABOUT_FILM) REFERENCES ALL_INFORMATION_ABOUT_FILM(ALL_INFORMATION_ABOUT_FILM_ID),
    CONSTRAINT USER_PROFILE_ID_CONSTRAINT FOREIGN KEY(USER_PROFILE_ID)REFERENCES USER_PROFILE(USER_PROFILE_ID)
)TABLESPACE CP_TS;
------------------------------------------------

CREATE TABLE tesst(
    tesst_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    informa varchar2(200)
    );

insert into tesst values(default,'sdsds');
insert into tesst values(default,'vccvvccv');
insert into tesst values(default,'cvcvcvcvcv');

------------------------------------------------



