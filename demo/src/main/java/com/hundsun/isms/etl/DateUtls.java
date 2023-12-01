package com.hundsun.isms.etl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtls {
    public static String getNow() {
        return new Date().toString();
    }
}
