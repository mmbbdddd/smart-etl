package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.exception.ConfigException;
import cn.hz.ddbm.setl.service.RuntimeContainerFactory;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

@Getter
public class Action {
    @NonNull
    String              step;
    @NonNull
    String              name;
    @NonNull
    ComponentRunner     componentRunner;
    @NonNull
    Map<String, Object> attrs;

    public Action(String stepCode, String name, String component, Map<String, Object> attrs) throws ConfigException {
        this.step            = stepCode;
        this.name            = name;
        this.componentRunner = null == component ? null : new ComponentRunner(component);
        this.attrs           = attrs;
        Assert.notNull(this.step, "step is null");
        Assert.notNull(this.name, "name is null");
    }

    public void execute(TaskRuntimeContext ctx) throws Exception {
        ctx.setAction(this);
        componentRunner.execute(ctx);
    }

    public void validate() {

    }

    public static class ComponentRunner {
        RuntimeContainerFactory.RuntimeType container;
        String                              component;
        String                              args;

        public ComponentRunner(String component) throws ConfigException {
            try {
//                String c = component;
//                String pattern = "(#.*?)(\\s{1}.*?)(\\s{1}.*)";
//                Pattern p1 = Pattern.compile(pattern);
//                Matcher matcher = p1.matcher(c);
//                this.container = RuntimeContainer.valueOf(matcher.group(1).replace("#", ""));
//                this.component = matcher.group(2);
//                this.args      = matcher.group(3);
                String[] splits = component.split("\\s");
                this.args = "";
                for (String part : splits) {
                    if (!StringUtils.isEmpty(part)) {
                        if (this.container == null) {
                            this.container = RuntimeContainerFactory.RuntimeType.valueOf(part.replace("#", ""));
                        } else if (this.component == null) {
                            this.component = part;
                        } else {
                            this.args += part;
                        }
                    }
                }
            } catch (Exception e) {
                throw new ConfigException(e, component);
            }
        }

        public void execute(TaskRuntimeContext ctx) {
            RuntimeContainerFactory.get(container).run(ctx, this);
        }
    }


}
