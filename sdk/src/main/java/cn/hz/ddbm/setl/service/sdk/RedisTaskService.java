package cn.hz.ddbm.setl.service.sdk;

import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.exception.ExecuteException;
import cn.hz.ddbm.setl.service.TaskFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RedisTaskService implements TaskService {
    Map<String, TaskRuntimeContext> datas = new HashMap<>();

    public RedisTaskService() {
        this.datas = new HashMap<>();
    }

    @Override
    public TaskRuntimeContext getOrCreateContext(Task task, TaskFactory.EtlTaskRequest request) throws IOException {
        return datas.computeIfAbsent(request.getTaskId(), new Function<String, TaskRuntimeContext>() {
            @Override
            public TaskRuntimeContext apply(String taskId) {
                return TaskRuntimeContext.create(request, task);
            }
        });
    }

    @Override
    public void updateFlowStatus(TaskRuntimeContext ctx) throws IOException {
        datas.put(ctx.getTaskId(), ctx);
    }
}
