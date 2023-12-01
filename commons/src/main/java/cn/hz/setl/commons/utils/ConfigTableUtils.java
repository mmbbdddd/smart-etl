package cn.hz.setl.commons.utils;

import cn.hz.setl.commons.annotation.ConfigTable;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.List;

public class ConfigTableUtils implements ApplicationContextAware {
    private static ApplicationContext ctx;

    public static <T> List<T> findAll(Class<T> type) {
        NamedParameterJdbcTemplate jdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
        String                     sql          = buildSqlByType(type);
        return jdbcTemplate.query(sql, Collections.emptyMap(), BeanPropertyRowMapper.newInstance(type));
    }

    private static <T> String buildSqlByType(Class<T> type) {
        ConfigTable configTableAnno = AnnotationUtils.findAnnotation(type, ConfigTable.class);
        if (null != configTableAnno) {
            return configTableAnno.sql();
        } else {
            TableName tableNameAnno = AnnotationUtils.findAnnotation(type, TableName.class);
            return "select * from " + tableNameAnno.value();
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigTableUtils.ctx = applicationContext;
    }
}
