package cn.hz.ddbm.setl.domain;

import cn.hutool.json.JSONUtil;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.exception.EtlRouteException;
import cn.hz.ddbm.setl.exception.EtlStepException;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.sdk.TaskService;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Data
@NoArgsConstructor
@Slf4j
public class Task {
    String     code;
    String     name;
    EngineType type;
    Boolean    fluent;

    @NonNull Map<String, Step> steps;
    @NonNull Step              startStep;
    @NonNull Step              failStep;
    @NonNull
    TaskService service;
    @NonNull
    TaskFactory factory;


    public Boolean getFluent() {
        return fluent;
    }

    public String routeTo(TaskRuntimeContext ctx, EtlStepException e) throws EtlRouteException {
        return null;
    }


    public void validate() {

    }

    public TaskFactory getTaskFactory() {
        return factory;
    }

    public Step getStep(String stepName) {
        return steps.get(stepName);
    }

    public void execute(TaskRuntimeContext ctx) {
        String engine = ctx.getTask().getType().name();
        try {
            loop:
            while (true) {
                if (ctx.isRunnable()) {
                    String oldStep  = ctx.getStep().getCode();
                    String nextStep = ctx.getStep().execute(ctx);
                    String action   = ctx.getAction().getCode();
                    ctx.updateTaskStep(nextStep);
                    log.info("{}任务状态变更:{},{},{},{},{}==>{},",engine, ctx.getTaskId(), code, ctx.getCommand(), action, oldStep, nextStep);
                } else {
                    log.info("{}任务已完成:{},{},{},{},{} ", engine,ctx.getTaskId(), code, ctx.getTaskStatus(), ctx.getStep().getType(),ctx.getStep().getCode());
                    break ;
                }
                if (ctx.getFluent()) {
                    continue loop;
                } else {
                    break;
                }
            }
        } catch (EtlStepException e) {
            String oldStep      = e.getStep().getCode();
            String failOverStep = null;
            try {
                failOverStep = getTaskFactory().exceptionRoute(ctx, e);
                ctx.updateTaskStep(failOverStep);
                log.warn("{}处理异常:{},{},{},{}==>{}",engine, ctx.getTaskId(), code, ctx.getCommand(), oldStep, failOverStep);
            } catch (EtlRouteException ex) {
                //路由异常应该为工作流定义错误，异常不抛出，任务状态设置为失败。
                ctx.updateTaskStatus(TaskStatus.fail, oldStep);
                log.error("{}路由异常:{},{},{}", engine,ctx.getTaskId(), code, oldStep, ex);
            }
        } catch (EtlRouteException re) {
            String oldStep = re.getStep().getCode();
            //路由异常应该为工作流定义错误，异常不抛出，任务状态设置为失败。
            ctx.updateTaskStatus(TaskStatus.fail, oldStep);
            log.error("{}路由异常:{},{},{}", engine,ctx.getTaskId(), code, oldStep, re);
        } finally {
            try {
                service.updateFlowStatus(ctx);
                log.debug("{}上下文更新:{},{},{}",engine, ctx.getTaskId(), code, JSONUtil.toJsonStr(ctx));
            } catch (IOException e) {
                log.error("{}运行时保存异常:{},{}}", engine,ctx.getTaskId(), JSONUtil.toJsonStr(ctx));
            }
        }

    }
}
