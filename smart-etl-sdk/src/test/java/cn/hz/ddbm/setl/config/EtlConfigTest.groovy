package cn.hz.ddbm.setl.config

import cn.hz.ddbm.setl.entity.BaseDataset
import cn.hz.ddbm.setl.mapper.BaseDatasetMapper
import cn.hz.ddbm.setl.common.utils.ValueObjectUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
@Import([EtlConfig.class, TestConfig.class])
public class EtlConfigTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void getValueObjectService() {
        BaseDatasetMapper m = ValueObjectUtils.getBeanForModel(ctx,BaseDataset.class);
        println m.get("dataset1")
    }

    static class TestConfig {

    }



}