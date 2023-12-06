--ADMIN LOCAL
    --По дефолту при создании admina через dbca ему выдается роль pdb_dba
    --в которой лежат привилегии на создание pdb и session,
    SELECT USERNAME,CREATED FROM DBA_USERS ORDER BY CREATED DESC;
    SELECT *FROM V$TABLESPACE;
    
    SELECT default_tablespace FROM dba_users WHERE username='ADMIN';
    select * from session_privs;
    select * from cdb_role_privs where grantee=upper('admin');
    select * from role_sys_privs where role='PDB_DBA';
    select * from dba_role_privs where GRANTEE='PDB_DBA';
    select * from role_sys_privs where ROLE='CONNECT';
    --PRIVILEGES
    GRANT CREATE ANY TABLE TO PDB_DBA;
    GRANT CREATE VIEW TO PDB_DBA;
    GRANT CREATE PROCEDURE TO PDB_DBA;
    GRANT CREATE TABLESPACE TO PDB_DBA;
    
    GRANT CREATE ANY DIRECTORY TO PDB_DBA;-- создать директорию для импорта/экспорта
    GRANT WRITE ON DIRECTORY MY_DIRECTORY TO PDB_DBA;
    --GRANT RESOURCE TO admin; 
    --ДЛЯ GENERATED ALWAYS AS IDENTITY
    GRANT CREATE ANY SEQUENCE TO PDB_DBA;
    GRANT SELECT ANY SEQUENCE TO PDB_DBA;
    --выдача quot в TS
    ALTER USER admin QUOTA unlimited ON CP_TS;
    COMMIT;
    
    CREATE TABLESPACE CP_TS
        DATAFILE 'CP_TS.DBF'
        SIZE 50M
        AUTOEXTEND ON NEXT 1M
        MAXSIZE 150M
        EXTENT MANAGEMENT LOCAL;
        
    CREATE TEMPORARY TABLESPACE CP_TEMP_TS
        TEMPFILE 'CP_TEMP_TS.DBF'
        SIZE 20M
        AUTOEXTEND ON NEXT 500K
        MAXSIZE 120M
        EXTENT MANAGEMENT LOCAL;
    
    COMMIT;

--USER PROFILE
    CREATE ROLE ROLE_USER;
    GRANT CREATE SESSION,
          CREATE PROCEDURE TO ROLE_USER;
          
          

