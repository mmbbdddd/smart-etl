package cn.hz.ddbm.setl.service.sdk.factory;

import cn.hutool.json.JSONUtil;
import cn.hz.ddbm.setl.domain.*;
import cn.hz.ddbm.setl.exception.EtlRouteException;
import cn.hz.ddbm.setl.config.EtlConfig;
import cn.hz.ddbm.setl.entity.EtlTask;
import cn.hz.ddbm.setl.entity.EtlTaskstep;
import cn.hz.ddbm.setl.entity.EtlTaskstepAction;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import cn.hz.setl.commons.utils.ValueObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Component
public class PipelineFactory extends BaseTaskFactory {

    @Override
    public Map<String, Task> initWorkFlows() {
        //从数据库定义中创建流程定义
        List<EtlTask>                        tasks       = ValueObjectUtils.findAll(ctx, EtlTask.class);
        List<EtlTaskstep>                    steps       = ValueObjectUtils.findAll(ctx, EtlTaskstep.class);
        List<EtlTaskstepAction>              actions     = ValueObjectUtils.findAll(ctx, EtlTaskstepAction.class);
        Map<String, List<EtlTaskstep>>       taskSteps   = steps.stream().collect(Collectors.groupingBy(EtlTaskstep::getTaskCode));
        Map<String, List<EtlTaskstepAction>> taskActions = actions.stream().collect(Collectors.groupingBy(EtlTaskstepAction::getTaskCode));
        List<Task> flows = tasks.stream().filter(task -> task.getType().equals(EngineType.PIPELINE)).map(task -> {
            try {
                Task flow = EtlTask.build(this, EngineType.PIPELINE, task, taskSteps.get(task.getTaskCode()), taskActions.get(task.getTaskCode()), ctx);
                log.debug("构建ETL工作流{}:{}", task.getTaskCode(), JSONUtil.toJsonStr(flow));
                return flow;
            } catch (Throwable e) {
                log.error("构建ETL工作流{}异常:", task.getTaskCode(), e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return flows.stream().collect(Collectors.toMap(Task::getCode, workFlow -> workFlow));
    }

    /**
     * 构建不同工作流的领域对象
     */
    @Override
    public Step dtoToDomain(EtlTaskstep dto, Task flow, Map<String, Action> actionMap) {
        Assert.notNull(dto.getAttr(EtlConfig.COAST.PIPELINE_INDEX_ATTR, Integer.class), "EtlTaskstep.attrs[index] is null");
        //优化：type可由index决定。
        Step step = new Step(dto.getStepCode(), dto.getName(), dto.getType(), actionMap, dto.getAttrs());
        //检查是否有index属性，没有则报错

        return step;
    }

    /**
     * pipeline模式 路由逻辑： 按照step.index顺序执行。
     */
    @Override
    public String normalRoute(TaskRuntimeContext ctx) throws EtlRouteException {
        return nextIndexStep(ctx.getTask(), ctx.getStep());
    }

    /**
     * pipeline模式结果路由逻辑：如果有异常，则失败。
     * 1，任务状态更新为fail
     * 2，节点状态保持原位
     * 3，异常消息
     *
     * @param ctx
     * @param e
     * @return
     * @throws EtlRouteException
     */
    @Override
    public String exceptionRoute(TaskRuntimeContext ctx, Exception e) throws EtlRouteException {
        return null;
    }


    private String nextIndexStep(Task task, Step step) {
        //如果是结束节点，直接返回
        if (step.getType().equals(StepType.fail) || step.getType().equals(StepType.su)) {
            return step.getCode();
        }
        Collection<Step> steps    = task.getSteps().values();
        Integer          index    = step.getAttr(EtlConfig.COAST.PIPELINE_INDEX_ATTR, Integer.class);
        Step             nextStep = steps.stream().filter(s -> s.getAttr(EtlConfig.COAST.PIPELINE_INDEX_ATTR, Integer.class) == (index + 1)).findFirst().orElse(null);
        return nextStep.getCode();
    }

}
