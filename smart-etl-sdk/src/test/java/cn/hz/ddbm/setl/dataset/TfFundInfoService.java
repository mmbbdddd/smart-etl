//package com.hundsun.etlengine.dataset;
//
//import org.springframework.beans.factory.InitializingBean;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TfFundInfoService implements InitializingBean , DatasetService.CacheFunctionService<TfFundInfoService.TfFundInfo> {
//    public Object get(String fundId) {
//        return null;
//    }
//
//    class TfFundInfo {
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        cacheFunctionMap = new HashMap<>();
//        cacheFunctionMap.put("get", new DatasetService.CacheFunction<TfFundInfo>() {
//            @Override
//            public Class type() {
//                return TfFundInfo.class;
//            }
//
//            @Override
//            public List<String> argsToCodes(Map<String,Object> namedArgs) {
//                return null;
//            }
//
//
//            @Override
//            public String argsToSql(Map<String,Object> namedArgs) {
//                return null;
//            }
//        });
//    }
//
//
//    Map<String, DatasetService.CacheFunction<TfFundInfo>> cacheFunctionMap;
//
//    public DatasetService.CacheFunction<TfFundInfo> getCacheFunction(String method) {
//        return cacheFunctionMap.get(method);
//    }
//
//}
