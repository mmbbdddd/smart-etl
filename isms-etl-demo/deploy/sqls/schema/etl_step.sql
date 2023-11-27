create table ETL_STEP
(
    TASK_CODE    VARCHAR2(32)  not null,
    STEP_CODE    VARCHAR2(32)  not null,
    STEP_DESC    VARCHAR2(512) not null,
    STEP_ACTIONS VARCHAR2(512) not null,
    TASK_ATTRS   VARCHAR2(512) not null
)

