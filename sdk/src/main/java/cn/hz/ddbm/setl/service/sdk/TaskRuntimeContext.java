package cn.hz.ddbm.setl.service.sdk;

import cn.hz.ddbm.setl.config.EtlConfig;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.StepType;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.service.TaskFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;


@Getter
public class TaskRuntimeContext {
    final String              taskId;
    final String              command;
    final Map<String, Object> args;
    final Task                task;
    @Setter
    @NonNull
    Step step;
    @Setter
    @NonNull
    TaskStatus taskStatus;
    //运行时
    @Setter
    Action    action;
    @Setter
    Object    result;
    @Setter
    Exception error;

    public TaskRuntimeContext(String taskId, String command, Map<String, Object> args, Task task) {
        Assert.notNull(task, "task is null");
        this.taskId  = taskId;
        this.command = null == command ? EtlConfig.COAST.DEFAULT_COMMAND : command;
        this.args    = null == args ? new HashMap<>() : args;
        this.task    = task;
    }

    public static TaskRuntimeContext create(TaskFactory.EtlTaskRequest request, Task task) {
        TaskRuntimeContext c = new TaskRuntimeContext(request.getTaskId(), request.getCommand(), request.getArgs(), task);
        c.step       = task.getStartStep();
        c.taskStatus = TaskStatus.ready;
        return c;
    }

    /**
     * 1,流程是可运行的(TaskStatus.ready)
     * 2,节点是可运行的!(StepType.start,StepType.job)
     * 3,执行次数没有超限
     *
     * @return
     */
    public boolean isRunnable() {
        return taskStatus.equals(TaskStatus.ready) && (step.getType().equals(StepType.start) || step.getType().equals(StepType.job));
    }


    public void updateTaskStep(String stepName) {
        this.step       = task.getStep(stepName);
        this.taskStatus = step.getType().getTaskStatus();
    }

    public void setTaskStatus(TaskStatus taskStatus, String stepName) {
        this.taskStatus = taskStatus;
    }


    public Action getAction() {
        if (null == action) {
            return EtlConfig.COAST.EMPTY_ACTION;
        }
        return action;
    }

    public boolean getFluent() {
        Boolean requestFluent = getArg(EtlConfig.COAST.FLUENT_ATTR, Boolean.class);
        return null != requestFluent ? requestFluent : (task.getFluent());
    }

    private <T> T getArg(String attrName, Class<T> type) {
        return (T) getArgs().get(attrName);
    }


    public String getErrorMessage() {
        return null;
    }

    @Override
    public String toString() {
        return "TaskRuntimeContext{" +
                "taskId='" + taskId + '\'' +
                ", args=" + args +
                ", task=" + task +
                ", step=" + step +
                '}';
    }
}
