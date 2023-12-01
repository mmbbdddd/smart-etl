package cn.hz.ddbm.setl.service.etlruntime;

import cn.hz.ddbm.setl.EtlTestConfig;
import cn.hz.ddbm.setl.config.EtlConfig;
import cn.hz.ddbm.setl.service.factory.PipelineFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import({EtlTestConfig.class, EtlConfig.class})
public class PipelineEtlServiceTest {
    @Autowired
    PipelineFactory pipelineEtlService;

    @Test
    public void initWorkFlows() {
        pipelineEtlService.initWorkFlows();
    }


}