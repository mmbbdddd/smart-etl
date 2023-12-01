package com.hundsun.isms.etl;

import cn.ddbm.pc.config.PcConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.hundsun.isms.etl")
@Import(PcConfiguration.class)
public class IsmsTestConfig {



}
