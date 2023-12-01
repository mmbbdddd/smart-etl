package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.entity.TaskStatus;
import lombok.Getter;

@Getter
public enum StepType {
    start(TaskStatus.ready, "已落地"), job(TaskStatus.ready, "进行中"), su(TaskStatus.fine, "业务成功"), fail(TaskStatus.fine, "业务失败");

    private final TaskStatus taskStatus;
    private final String     desc;

    StepType(TaskStatus taskStatus, String desc) {
        this.taskStatus = taskStatus;
        this.desc       = desc;
    }
}
