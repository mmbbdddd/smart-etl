create table ETL_TASK
(
    TASK_CODE           VARCHAR2(32) not null,
    TASK_NAME           VARCHAR2(128) not null,
    TASK_DESCR          VARCHAR2(512) not null,
    CREATE_TIME         NUMBER(14, 6) default 0.0 not null,
    UPDATE_TIME         NUMBER(14, 6) default 0.0,
    TASK_STATE_PROVIDER VARCHAR2(128),
    TASK_MODE           VARCHAR2(16) not null,
    IS_TRANCATIONAL     NUMBER not null
)

