//package com.hundsun.etlengine.dataset;
//
//
//import cn.hz.ddbm.setl.common.utils.SpringUtils;
//import org.springframework.cache.Cache;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.util.Assert;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class DatasetService {
//    Map<String, DataSet> dataSetMap;
//
//
//    public DataSet get(String dataCode) {
//        return dataSetMap.get(dataCode);
//    }
//
//
//    /**
//     * 适配客户段代码适配了CacheFunctionService接口提供CacheFunction的情况
//     *
//     * @param dataSetCode
//     * @param method
//     * @param namedArgs
//     * @param cfs
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> query(String dataSetCode, String method, Map<String, Object> namedArgs, CacheFunctionService<T> cfs) {
//        return query(dataSetCode, namedArgs, cfs.getCacheFunction(method));
//    }
//
//    /**
//     * 读方法：根方法
//     *
//     * @param dataSetCode
//     * @param namedArgs
//     * @param cf
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> query(String dataSetCode, Map<String, Object> namedArgs, CacheFunction<T> cf) {
//        DatasetService datasetService = SpringUtils.getBean(DatasetService.class);
//        DataSet dataSet = datasetService.get(dataSetCode);
//        Assert.notNull(dataSet, "数据集为空:" + dataSetCode);
//        Cache cache = getOrCreate(dataSet);
//        if (dataSet.getIsCache()) {
//            Set<String> cacheKeys = cacheKeys(dataSet, namedArgs, cf);
//            return cacheKeys.stream().map(cacheKey -> {
//                return cache.get(cacheKey, () -> datasetService.callback(dataSet, namedArgs, cf.type(), cf));
//            }).flatMap(Collection::stream).collect(Collectors.toList());
//        } else {
//            return datasetService.callback(dataSet, namedArgs, cf.type(), cf);
//        }
//    }
//
//    public Set<String> cacheKeys(DataSet dataSet, Map<String, Object> args, CacheFunction cf) {
//        return (Set<String>) cf.argsToCodes(args).stream().map(code -> {
//            args.put("args_to_code", code);
//            return TemplateUtils.render(dataSet.code, args);
//        }).filter(Objects::nonNull).collect(Collectors.toSet());
//    }
//
//    public <T> List<T> callback(DataSet dataSet, Map<String, Object> args, Class<T> type, CacheFunction<T> cf) {
//        args.put("args_to_sql", cf.argsToSql(args));
//        String sql = TemplateUtils.render(dataSet.sql, args);
//        List<T> list = SpringUtils.getBean(NamedParameterJdbcTemplate.class).query(sql, args, BeanPropertyRowMapper.newInstance(type));
//        return list;
//    }
//
//
//    /**
//     * 获取或者创建缓存
//     *
//     * @param dataSet
//     * @return
//     */
//
//    private static Cache getOrCreate(DataSet dataSet) {
//        //TODO
//        EhCacheCacheManager cm = SpringUtils.getBean(EhCacheCacheManager.class);
////        cm.getCacheManager().addCache(null);
//        return null;
//    }
//
//
//    public void refresh(String dataSetCode) {
//        //todo
//    }
//
//    public void refreshAll() {
//        //todo
//    }
//
//    public void remove(String dataSetCode) {
//        //todo
//    }
//
//    public void removeAll() {
//        //todo
//    }
//
//    interface CacheFunction<T> {
//
//        Class<T> type();
//
//        List<String> argsToCodes(Map<String, Object> args);
//
//        String argsToSql(Map<String, Object> args);
//    }
//
//    interface CacheFunctionService<T> {
//        DatasetService.CacheFunction<T> getCacheFunction(String method);
//    }
//}
