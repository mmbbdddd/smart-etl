package cn.hz.ddbm.setl.service.sdk;

import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.model.EtlTaskRequest;
import cn.hz.ddbm.setl.service.TaskFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RedisTaskService implements TaskService {

    @Override
    public TaskRuntimeContext getOrCreateContext(Task task, EtlTaskRequest request, TaskFactory taskFactory) throws IOException {
        return null;
    }

    @Override
    public void updateFlowStatus(TaskRuntimeContext ctx) throws IOException {

    }
}
