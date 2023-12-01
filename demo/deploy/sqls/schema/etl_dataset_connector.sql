create table ETL_DATASET_CONNECT
(
    DATASET_CODE   VARCHAR2(32) not null,
    DATASET_TYPE   VARCHAR2(32) not null,
    ATTRS          VARCHAR2(512),
    MAX_TPS        VARCHAR2(32),
    MAX_BATCH_SIZE VARCHAR2(32),
    CRON           VARCHAR2(32),
    MAX_DPS        VARCHAR2(32),
    MEMO           VARCHAR2(512)
)

