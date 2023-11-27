//package com.hundsun.etlengine.service;
//
//import cn.ddbm.pc.FlowStatus;
//import com.hundsun.etlengine.components.*;
//import com.hundsun.etlengine.domain.base.BaseStep;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * 功能:
// * <p>
// * 作者: wanglin @ 2023/11/1
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ComponentServiceTest {
//    @Autowired
//    ComponentService s;
//
//    //测试工厂
//    @Test
//    public void testGet() {
//        JavaRunner   sql    = s.get("table_sync");
//        PythonRunner python = s.get("dbf_import");
//        ShellRunner  shell  = s.get("disk_check");
//    }
//
//    /**
//     * 测试组件执行
//     * 1，生成ETL步骤
//     * 2，执行步骤的action，触发组件执行
//     */
//    @Test
//    public void exec() {
//        FlowStatus ctx = null;
//        try {
//            mockStepEntity().getEtlAction().execute(null);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 生成一个模拟步骤
//     *
//     * @return
//     */
//    private BaseStep mockStepEntity() {
//        return null;
//    }
//
//
//}