//package com.hundsun.isms.etl.hs;
//
//
//import cn.ddbm.pc.Dicts;
//import cn.hz.ddbm.setl.failover.*;
//import cn.hz.ddbm.setl.PipelineTask;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class HsGzTask extends PipelineTask {
//    public HsGzTask() {
//        super("hsGzTask", "测试任务");
//    }
//
//    @Override
//    public List pipeline() {
//        return Arrays.asList(
//                new Shell("ping 127.0.0.1").setJob("ping net", "判断网络").setType(Dicts.StepType.START),
//                new Shell("java -version").setJob("jdk", "判断JDK"),
//                DBF_Mysql.builder().build().setJob("dbf", "导入文件"),
//                new Mysql_LoadData("").setJob("loadDate", "导数"),
//                new Mysql_Schema("").setJob("init", "创建数据库"),
//                new Mysql_Sql("").setJob("executeSql1", "执行sql").setType(Dicts.StepType.END));
//    }
//}
