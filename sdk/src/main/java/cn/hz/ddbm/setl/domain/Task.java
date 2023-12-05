package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.config.EtlConfig;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.exception.RouteExecuteException;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.sdk.TaskService;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@Slf4j
public class Task {
    @NonNull String            code;
    @NonNull String            name;
    @NonNull TaskType          type;
    @NonNull Boolean           fluent;
    @NonNull Map<String, Step> steps;
    @NonNull Step              startStep;
    @NonNull Step              failStep;
    @NonNull
    TaskService service;
    @NonNull
    TaskFactory taskFactory;


    public Boolean getFluent() {
        return fluent;
    }

    public String routeTo(TaskRuntimeContext ctx, ExecuteException e) throws RouteExecuteException {
        return null;
    }


    public void validate() {
        steps.forEach((stepCode, step) -> step.validate());
        startStep.validate();
    }


    public Step getStep(String stepName) {
        return steps.get(stepName);
    }

    public void execute(TaskRuntimeContext ctx) {
        String engine  = ctx.getTask().getType().name();
        String oldStep = null;
        try {
            loop:
            while (true) {
                if (ctx.isRunnable()) {
                    oldStep = ctx.getStep().getCode();
                    String nextStep = ctx.getStep().execute(ctx);
                    String action   = ctx.getAction().getName();
                    ctx.updateTaskStep(nextStep);
                    log.info("{}任务状态变更:{},{},{},{},{}==>{},", engine, ctx.getTaskId(), code, ctx.getCommand(), action, oldStep, nextStep);
                } else {
                    log.info("{}任务已完成:{},{},{},{},{} ", engine, ctx.getTaskId(), code, ctx.getTaskStatus(), ctx.getStep().getType(), ctx.getStep().getCode());
                    break;
                }
                if (ctx.getFluent()) {
                    continue loop;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            String nextStep = null;
            try {
                nextStep = ctx.getTaskFactory().onException(ctx, e);
                ctx.setStep(ctx.getTask().getStep(nextStep));
                //路由异常应该为工作流定义错误，异常不抛出，任务状态设置为失败。
                ctx.setTaskStatus(ctx.getStep().getType().getTaskStatus(), nextStep);
                log.error("{}异常:{},{},{},{},{}", engine, ctx.getTaskId(), code, oldStep, nextStep, e);
            } catch (Exception ex) {
                ctx.setTaskStatus(TaskStatus.fail, EtlConfig.FAIL_STEP.name);
                log.error("{}异常:{},{},{},{},{}", engine, ctx.getTaskId(), code, oldStep, nextStep, ex);
            }
        } finally {
            try {
                service.updateFlowStatus(ctx);
                log.debug("{}上下文更新:{},{},{}", engine, ctx.getTaskId(), code, ctx.toString());
            } catch (IOException e) {
                log.error("{}运行时保存异常:{},{}}", engine, ctx.getTaskId(), ctx.toString());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return Objects.equals(code, task.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "code='" + code + '\'' +
                '}';
    }

    public TaskFactory getTaskFactory() {
        return taskFactory;
    }
}
