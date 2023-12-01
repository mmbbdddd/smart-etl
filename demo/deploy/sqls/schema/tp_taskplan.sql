-- -----------------------------------------------
-- 全量脚本
-- -----------------------------------------------
-- 创建表 tp_taskplan(执行计划)的当前表
CREATE TABLE etl_taskplan
(
    plan_id              varchar2(32)               NOT NULL,
    task_id              int            DEFAULT 0   NOT NULL,
    task_code            varchar2(32)               NOT NULL,
    batch_total          int                        NOT NULL,
    batch_no             int                        NOT NULL,
    batch_no_task_status varchar2(32)               NOT NULL,
    create_time          decimal(14, 6) DEFAULT 0.0 NOT NULL,
    update_time          decimal(14, 6) DEFAULT 0.0 NOT NULL
);

