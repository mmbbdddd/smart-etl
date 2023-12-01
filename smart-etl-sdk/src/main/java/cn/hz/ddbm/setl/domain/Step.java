package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.exception.EtlRouteException;
import cn.hz.ddbm.setl.exception.EtlStepException;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Getter;

import java.util.Map;


@Getter
public class Step {
    String              code;
    String              name;
    StepType            type;
    Map<String, Action> actions;
    Map<String, Object> attrs;

    public Step(String stepCode, String name, StepType type, Map<String, Action> actionMap, Map<String, Object> attrs) {
        this.code    = stepCode;
        this.name    = name;
        this.type    = type;
        this.actions = actionMap;
        this.attrs   = attrs;
    }


    public String execute(TaskRuntimeContext ctx) throws EtlStepException, EtlRouteException {
        return ctx.getTask().getTaskFactory().normalRoute(ctx);
    }

    public <T> T getAttr(String attrName, Class<T> type) {
        return (T) attrs.get(attrName);
    }
}
