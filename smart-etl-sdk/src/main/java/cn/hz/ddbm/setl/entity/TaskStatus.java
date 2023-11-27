package cn.hz.ddbm.setl.entity;

import lombok.Getter;

@Getter
public enum TaskStatus {
    ready("可执行"), fine("业务完成"), exception("技术异常"), fail("技术失败"), cancel("已取消"), pause("已暂停");

    private final String desc;

    TaskStatus(String desc) {
        this.desc = desc;
    }
}
