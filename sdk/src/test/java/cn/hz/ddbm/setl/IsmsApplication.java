package cn.hz.ddbm.setl;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( )
@MapperScan(value = "com.hundsun.etl.mapper")
public class IsmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsmsApplication.class, args);
    }
}
