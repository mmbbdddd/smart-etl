package com.hundsun.isms.etl.hs;


import cn.ddbm.pc.FlowProcessor;
import com.hundsun.isms.etl.IsmsTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = IsmsTestConfig.class)
public class HsGzTaskTest {

    @Autowired
    FlowProcessor flowService;

    @Test
    public void tt() {
        flowService.execute("hsGzTask", 1, null, null, true);
    }

}