package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.exception.RouteExecuteException;
import cn.hz.ddbm.setl.model.EtlTaskRequest;
import cn.hz.ddbm.setl.model.EtlTaskResponse;
import cn.hz.ddbm.setl.model.EtlTaskStatus;
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







}
