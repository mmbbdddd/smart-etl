package cn.hz.ddbm.setl.service.etlruntime;

import cn.hz.ddbm.setl.EtlTestConfig;
import cn.hz.ddbm.setl.config.EtlConfig
import cn.hz.ddbm.setl.exception.ExecuteException
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.factory.PipelineFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import([EtlTestConfig.class, EtlConfig.class])
@ComponentScan("")
public class PipelineEtlServiceTest {
    @Autowired
    PipelineFactory pipelineEtlService;

    @Test
    public void initWorkFlows() {
        pipelineEtlService.initWorkFlows();
    }

    @Test
    public void executeTask() {
        try {
            TaskFactory.EtlTaskResponse resp = pipelineEtlService.executeTask(new TaskFactory.EtlTaskRequest(
                    taskCode: "etlTest",
                    fluent: true
            ));
            println(resp.getTaskStatus())
//            Assert.assertEquals(resp.getTaskStatus().equals(TaskStatus.ing),"")
        } catch (ExecuteException e) {
            throw new RuntimeException(e);
        }
    }

}