package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.exception.RouteExecuteException;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.EntryTaskstep;
import cn.hz.ddbm.setl.entity.TaskStatus;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public interface TaskFactory {


    Map<String, Task> allWorkflows();


    /**
     * 执行任务调度
     *
     * @param request
     * @return
     */
    EtlTaskResponse executeTask(EtlTaskRequest request) throws ExecuteException, IOException;

    /**
     * 更新流程状态 到运行时（区别于持久态）
     *
     * @param taskId
     * @throws ExecuteException
     */
    void updateTaskStatus(String taskId, EtlTaskStatus taskStatus) throws ExecuteException;

    /**
     * 从属性中赋值到step
     *
     * @param flow
     * @param step
     * @return
     */
    Step dtoToDomain(EntryTaskstep dto, Task flow, Map<String, Action> actionMap);

    String route(TaskRuntimeContext ctx) throws RouteExecuteException;

    String onException(TaskRuntimeContext ctx, Exception e) throws Exception;


    //VO

    @Data
    class EtlTaskResponse implements Serializable {
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

    @Data
    class EtlTaskRequest implements Serializable {
        String              taskId;
        String              taskCode;
        String              command;
        Map<String, Object> args;
        Boolean             fluent;
    }

    @Data
    class EtlTaskStatus implements Serializable {
        String     taskId;
        TaskStatus flowStatus;
        String     flowStep;
        String     message;

    }

}
