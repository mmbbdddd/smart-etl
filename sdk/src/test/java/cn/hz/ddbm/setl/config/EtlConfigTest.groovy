package cn.hz.ddbm.setl.config

import cn.hz.ddbm.setl.entity.EntryDatasource
import cn.hz.setl.commons.utils.ConfigTableUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
@Import([EtlConfig.class ])
public class EtlConfigTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void getValueObjectService() {
        List<EntryDatasource> dss =  ConfigTableUtils.findAll(EntryDatasource.class);
        Assert.assertNotNull(dss)
    }



}