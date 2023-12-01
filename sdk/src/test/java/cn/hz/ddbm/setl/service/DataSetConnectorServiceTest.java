//package com.hundsun.etlengine.service;
//
//
//import com.hundsun.etlengine.connector.APIConnector;
//import com.hundsun.etlengine.connector.FileConnector;
//import com.hundsun.etlengine.connector.JdbcConnector;
//import com.hundsun.etlengine.connector.T2Connector;
//import org.junit.Test;
//
///**
// * 功能:
// * <p>
// * 作者: wanglin @ 2023/11/1
// **/
//public class DataSetConnectorServiceTest {
//
//    DataSetConnectorService dataSetConnector;
//
//    //测试API连接器
//    @Test
//    public void testGet() {
//        APIConnector  api  = dataSetConnector.get("http_fa_get_fund");
//        T2Connector   t2   = dataSetConnector.get("t2_fa_get_fund");
//        JdbcConnector jdbc = dataSetConnector.get("oracl237");
//        FileConnector file = dataSetConnector.get("dbf_zhongdeng");
//    }
//
//    @Test
//    public void tetApi() {
//        APIConnector api = dataSetConnector.get("http_fa_get_fund");
//        api.invoke();
//    }
//
//    @Test
//    public void testT2() {
//        T2Connector api = dataSetConnector.get("t2_fa_get_fund");
//        api.invoke();
//    }
//
//    @Test
//    public void testJdbc() {
//        JdbcConnector oracl237 = dataSetConnector.get("oracl237");
//        JdbcConnector gauss207 = dataSetConnector.get("gauss207");
////        备注，本方法仅用于测试，实际实现使用jdbctemplate或者mybatis的代理方法。
//        oracl237.executeSql("select 1 from dual");
//        gauss207.executeSql("select 1  ");
//    }
//}