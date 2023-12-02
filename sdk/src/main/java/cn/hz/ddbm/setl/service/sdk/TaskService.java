package cn.hz.ddbm.setl.service.sdk;

import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.service.TaskFactory;

import java.io.IOException;

public interface TaskService {
    TaskRuntimeContext getOrCreateContext(Task task, TaskFactory.EtlTaskRequest request) throws IOException;

    void updateFlowStatus(TaskRuntimeContext ctx) throws IOException;
}
