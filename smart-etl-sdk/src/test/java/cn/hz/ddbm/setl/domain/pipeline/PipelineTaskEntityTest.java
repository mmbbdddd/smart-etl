//package com.hundsun.etlengine.domain.pipeline;
//
//import cn.ddbm.pc.utils.JsonUtils;
//import cn.ddbm.pc.utils.SpringUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
///**
// * 功能:
// * <p>
// * 作者: wanglin @ 2023/11/3
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class PipelineTaskEntityTest {
//
//    /**
//     * 验证配置=>flow的转换是否OK
//     */
//    @Test
//    public void pipelineGet() {
//        try {
//            List<TaskEntity>  tasks = SpringUtils.getBean(ValueObjectService.class).findAll(TaskEntity.class);
//            List<TaskStepEntity> steps = SpringUtils.getBean(ValueObjectService.class).findAll(TaskStepEntity.class);
//            EtlPipelineTask      task  = (EtlPipelineTask) tasks.get(0).toValueObject(null);
//            List<EtlPipelineTask.PipelineStep> _steps = steps.stream().map(t -> {
//                try {
//                    return (EtlPipelineTask.PipelineStep) t.toValueObject(null);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }).filter(Objects::nonNull).collect(Collectors.toList());
//
//            System.out.println(JsonUtils.toJson(task.getAttrs()));
//            System.out.println(JsonUtils.toJson(steps));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}