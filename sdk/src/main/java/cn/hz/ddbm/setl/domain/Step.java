package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.exception.ArgsException;
import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;


@Getter
public class Step {
    @NonNull
    String              code;
    @NonNull
    String              name;
    @NonNull
    StepType            type;
    @NonNull
    Map<String, Action> actions;
    @NonNull
    Map<String, Object> attrs;

    public Step(String stepCode, String name, StepType type, Map<String, Action> actionMap, Map<String, Object> attrs) {
        this.code    = stepCode;
        this.name    = name;
        this.type    = type;
        this.actions = actionMap;
        this.attrs   = attrs;
    }


    public String execute(TaskRuntimeContext ctx) throws ExecuteException, ArgsException {
        ctx.setStep(this);
        Action action = actions.get(ctx.getCommand());
        if (null == action) {
            throw ArgsException.noActionForCommand(ctx.getTask().getCode(), this, ctx.getCommand());
        }
        try {
            action.execute(ctx);
        } catch (Exception e) {
            throw new ExecuteException(this, action, e);
        }
        return ctx.getTask().getTaskFactory().route(ctx);
    }

    public <T> T getAttr(String attrName, Class<T> type) {
        return (T) attrs.get(attrName);
    }

    public void validate() {
        actions.forEach((actionName, action) -> {
            action.validate();
        });
    }
}
