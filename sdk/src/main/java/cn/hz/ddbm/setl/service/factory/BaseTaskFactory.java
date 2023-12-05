package cn.hz.ddbm.setl.service.factory;

import cn.hutool.json.JSONUtil;
import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.model.EtlTaskRequest;
import cn.hz.ddbm.setl.model.EtlTaskResponse;
import cn.hz.ddbm.setl.model.EtlTaskStatus;
import cn.hz.ddbm.setl.service.sdk.TaskService;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import cn.hz.setl.commons.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "etl-engine")
public abstract class BaseTaskFactory implements TaskFactory, InitializingBean, ApplicationContextAware {
    protected Map<String, Task>  workFlowMap = new HashMap<>();
    protected ApplicationContext ctx;

    @Override
    public Map<String, Task> allWorkflows() {
        return workFlowMap;
    }

    @Override
    public EtlTaskResponse executeTask(EtlTaskRequest request) throws ExecuteException, IOException {
//          0 请求预处理，生成id
//        1,检查参数是否合法：有taskCode
//                 错误则返回
//        2，获取流程定义
//                 没有则返回
//        3，获取任务实例和状态（context）
//                  则继续，没有则新建
//        4，检查工作流状态是否可执行
//                  不可执行则返回
//        5，获取步骤
//                   执行
//        6，goto3

        if (StringUtils.isEmpty(request.getTaskId())) {
            request.setTaskId(UUIDUtils.uuid());
        }

        log.info("任务受理{},{},{},{}", request.getTaskId(), request.getTaskCode(), request.getCommand(), JSONUtil.toJsonStr(request.getArgs()));
        Assert.notNull(request.getTaskCode(), "EtlTaskRequest.taskCode is null");
        Task task = getWorkFlow(request.getTaskCode());
        Assert.notNull(task, "Task.taskCode is null:" + request.getTaskCode());
        TaskService        taskService = task.getService();
        TaskRuntimeContext ctx         = taskService.getOrCreateContext(task, request,this);
//       步骤 3456
        task.execute(ctx);
        return EtlTaskResponse.of(ctx);
    }


    public void updateTaskStatus(String taskId, TaskStatus flowStatus) throws ExecuteException {

    }

    @Override
    public void updateTaskStatus(String taskId, EtlTaskStatus taskStatus) throws ExecuteException {

    }

    private Task getWorkFlow(String taskCode) {
        return workFlowMap.get(taskCode);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.workFlowMap = initWorkFlows();
    }

    public abstract Map<String, Task> initWorkFlows();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
