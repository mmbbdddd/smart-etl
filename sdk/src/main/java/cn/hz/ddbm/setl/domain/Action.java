package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.service.Component;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
public class Action {
    @NonNull
    String              stepCode;
    @NonNull
    String              name;
    @NonNull
    Component           component;
    @NonNull
    Map<String, Object> attrs;

    public Action(String stepCode, String name, Component component, Map<String, Object> attrs) {
        this.stepCode     = stepCode;
        this.name      = name;
        this.component = component;
        this.attrs     = attrs;
    }

    public void execute(TaskRuntimeContext ctx) {
        ctx.setAction(this);
    }

    public void validate() {

    }
}
