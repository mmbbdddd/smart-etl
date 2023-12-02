package cn.hz.ddbm.setl.service.etlruntime;

import cn.hz.ddbm.setl.EtlTestConfig;
import cn.hz.ddbm.setl.config.EtlConfig
import cn.hz.ddbm.setl.exception.EtlException;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.factory.PipelineFactory
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import([EtlTestConfig.class, EtlConfig.class])
public class BaseEtlServiceTest {

    @Autowired
    PipelineFactory pipelineEtlService;

    @Test
    public void executeTask() {
        try {
            TaskFactory.EtlTaskResponse resp = pipelineEtlService.executeTask(new TaskFactory.EtlTaskRequest(
                    taskCode: "etlTest",
                    fluent: true
            ));
            println(resp.getTaskStatus())
//            Assert.assertEquals(resp.getTaskStatus().equals(TaskStatus.ing),"")
        } catch (EtlException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cancelTask() {
        pipelineEtlService.updateTaskStatus();
    }

    @Test
    public void pauseTask() {
        pipelineEtlService.executeTask()
    }
}