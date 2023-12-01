package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Action {
    @NonNull
    Step                step;
    @NonNull
    String              action;
    @NonNull
    String              name;
    @NonNull
    Map<String, Object> attrs;

    public Action(String alias, String name, Map<String, Object> attrs) {
        this.action = alias;
        this.name   = name;
        this.attrs  = attrs;
    }

    public void execute(TaskRuntimeContext ctx) {
        ctx.setAction(this);
    }

    public void validate() {

    }
}
