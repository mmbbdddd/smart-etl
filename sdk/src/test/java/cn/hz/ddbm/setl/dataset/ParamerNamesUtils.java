//package com.hundsun.etlengine.dataset;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
//import org.springframework.core.ParameterNameDiscoverer;
//import org.springframework.core.StandardReflectionParameterNameDiscoverer;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Map;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//
//
//public class ParamerNamesUtils {
//
//    ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
//    public String[] methoArgsName(Method method){
//       return discoverer.getParameterNames(method);
//    }
//
//    @Test
//    public void tt(){
//        ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
//        Method method = ReflectionUtils.findMethod(InnerClass.class, "method", List.class);
//        String[] actualParams = methoArgsName(method);
//        Assert.assertArrayEquals(actualParams, new String[]{"users"});
//    }
//
//    public static class InnerClass {
//        public void method(@PathVariable("username") @RequestBody final List<Map<String, List<Integer>>> users) {
//        }
//    }
//}
