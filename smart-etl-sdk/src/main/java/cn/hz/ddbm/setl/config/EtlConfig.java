package cn.hz.ddbm.setl.config;

import cn.hutool.extra.spring.SpringUtil;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.StepType;
import cn.hz.ddbm.setl.service.sdk.RedisTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class EtlConfig {

    @Bean
    RedisTaskService redisTaskService() {
        return new RedisTaskService();
    }

    @Bean
    SpringUtil springUtils() {
        return new SpringUtil();
    }


    public static class COAST {
        public static final String PIPELINE_INDEX_ATTR = "index";
        public static final String FLUENT_ATTR         = "fluent";
        public static final String DEFAULT_SEREVICE    = "redisTaskService";
        public static final String DEFAULT_COMMAND     = "submit";
        public static final Step FAIL_STEP           = new Step("fail", "失败节点", StepType.fail, Collections.emptyMap(), Collections.emptyMap());
        public static final Action EMPTY_ACTION        = new Action("_emptyAction", "无逻辑Action");

    }
}
