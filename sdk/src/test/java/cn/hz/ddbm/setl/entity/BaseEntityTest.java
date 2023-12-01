//package com.hundsun.etlengine.entity;
//
//import cn.ddbm.pc.utils.SpringUtils;
//import cn.ddbm.pc.utils.StringUtils2;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
///**
// * 功能:
// * <p>
// * 作者: wanglin @ 2023/11/3
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class BaseEntityTest {
//
//
//    @Test
//    public void findAll() {
//        List<ComponentEntity>  cs = SpringUtils.getBean(ValueObjectService.class).findAll(ComponentEntity.class);
//        List<DataSetConnectorEntity>  ds = SpringUtils.getBean(ValueObjectService.class).findAll(DataSetConnectorEntity.class);
//        List<TaskEntity>  ts = SpringUtils.getBean(ValueObjectService.class).findAll(TaskEntity.class);
//        List<TaskStepEntity>  tss = SpringUtils.getBean(ValueObjectService.class).findAll(TaskStepEntity.class);
//
//
//        Assert.isTrue(cs.size() !=0);
//        Assert.isTrue(ds.size() !=0);
//        Assert.isTrue(ts.size() !=0);
//        Assert.isTrue(tss.size() !=0);
//    }
//
//    @Test
//    public void getIdColumn(){
//        Assert.isTrue(SpringUtils.getBean(ValueObjectService.class).getIdColumn(ComponentEntity.class).equals("code"));
//    }
//    @Test
//    public void st(){
//        System.out.println(StringUtils2.humpToLine2("abcCef"));
//    }
//}