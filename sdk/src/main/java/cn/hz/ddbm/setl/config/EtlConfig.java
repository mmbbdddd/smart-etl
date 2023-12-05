package cn.hz.ddbm.setl.config;

import cn.hutool.extra.spring.SpringUtil;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.StepType;
import cn.hz.ddbm.setl.exception.ConfigException;
import cn.hz.ddbm.setl.service.Component;
import cn.hz.ddbm.setl.service.sdk.MemTaskService;
import cn.hz.ddbm.setl.service.sdk.RedisTaskService;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;
import cn.hz.setl.commons.utils.ConfigTableUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class EtlConfig {

    @Bean
    MemTaskService redisTaskService() {
        return new MemTaskService();
    }

    @Bean
    SpringUtil springUtils() {
        return new SpringUtil();
    }

    @Bean
    ConfigTableUtils configTableUtils() {
        return new ConfigTableUtils();
    }


    public static final Step FAIL_STEP = new Step("fail", "失败节点", StepType.fail, Collections.emptyMap(), Collections.emptyMap());

    public static final Action                 EMPTY_ACTION;
    public static final Action.ComponentRunner EMPTY_COMPONENT;

    static {
        try {
            EMPTY_ACTION = new Action("_emptyAction", "无逻辑Action", null, Collections.emptyMap());
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
        EMPTY_COMPONENT = new Action.ComponentRunner() {
        };
    }


    public static class COAST {
        public static final String PIPELINE_INDEX_ATTR = "index";
        public static final String FLUENT_ATTR         = "fluent";
        public static final String DEFAULT_SEREVICE    = "redisTaskService";
        public static final String DEFAULT_COMMAND     = "submit";


    }
}
