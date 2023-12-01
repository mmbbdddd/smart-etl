package com.hundsun.isms.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hundsun.isms.etl")
//@ImportAutoConfiguration(classes = HotUpdateConfiguration.class)
public class IsmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsmsApplication.class, args);
    }
}
