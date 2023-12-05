package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.domain.Step;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:
 * <p>
 * 作者: wanglin @ 2023/11/1
 **/

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ComponentServiceTest {
    @Autowired
    ComponentService s;

    //测试工厂
    @Test
    public void testGet() {
//        JavaRunner   sql    = s.get("table_sync");
//        PythonRunner python = s.get("dbf_import");
//        ShellRunner  shell  = s.get("disk_check");
    }

    /**
     * 测试组件执行
     * 1，生成ETL步骤
     * 2，执行步骤的action，触发组件执行
     */
    @Test
    public void exec() {

    }

    /**
     * 生成一个模拟步骤
     *
     * @return
     */
    private Step mockStepEntity() {
        return null;
    }


}