create table ETL_COMPONENT
(
    CPNT_CODE        VARCHAR2(32) not null,
    CPNT_NAME        VARCHAR2(128) not null,
    LAYER            VARCHAR2(16) not null,
    EXEC_ENGINE      VARCHAR2(16) not null,
    EXECUTOR_HANDLER VARCHAR2(128) not null,
    MEMO             VARCHAR2(512) not null,
    ATTR             VARCHAR2(512) not null
)


