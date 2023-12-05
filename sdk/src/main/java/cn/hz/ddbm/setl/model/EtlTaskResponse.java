package cn.hz.ddbm.setl.model;

import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Data;

import java.io.Serializable;

@Data
public class EtlTaskResponse implements Serializable {
    String     taskId;
    String     taskCode;
    TaskStatus taskStatus;
    String     flowStep;
    String     message;

    public static EtlTaskResponse of(TaskRuntimeContext ctx) {
        EtlTaskResponse s = new EtlTaskResponse();
        s.taskId     = ctx.getTaskId();
        s.taskCode   = ctx.getTask().getCode();
        s.taskStatus = ctx.getTaskStatus();
        s.flowStep   = ctx.getStep().getCode();
        s.message    = ctx.getErrorMessage();
        return s;
    }
}
