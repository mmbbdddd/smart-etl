package cn.hz.ddbm.setl.domain;

import cn.hz.ddbm.setl.config.EtlConfig;
import cn.hz.ddbm.setl.exception.ConfigException;
import cn.hz.ddbm.setl.service.RuntimeFactory;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.componentRunner = StringUtils.isEmpty(component) ? EtlConfig.EMPTY_COMPONENT : new ComponentRunner(component);
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
        RuntimeFactory.RuntimeType runtimeType;
        String                     component;
        String                     args;

        public ComponentRunner(String component) throws ConfigException {
            try {
//                String  c       = component;
//                String  pattern = "(#.*?)(\\s{1}.*?)(\\s{1}.*)";
//                Pattern p1      = Pattern.compile(pattern);
//                Matcher matcher = p1.matcher(c);
//                this.runtimeType = RuntimeFactory.RuntimeType.valueOf(matcher.group(1).replace("#", ""));
//                this.component   = matcher.group(2);
//                this.args        = matcher.group(3);


                String[] splits = component.split("\\s");
                this.args = "";
                for (String part : splits) {
                    if (!StringUtils.isEmpty(part)) {
                        if (this.runtimeType == null) {
                            this.runtimeType = RuntimeFactory.RuntimeType.valueOf(part.replace("#", ""));
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

        public ComponentRunner() {

        }

        public void execute(TaskRuntimeContext ctx) {
            RuntimeFactory.get(runtimeType).run(ctx, this);
        }
    }


}
