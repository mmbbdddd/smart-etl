package cn.hz.ddbm.setl.service.factory;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.EntryTaskstep;
import cn.hz.ddbm.setl.exception.RouteExecuteException;
import cn.hz.ddbm.setl.model.EtlTaskRequest;
import cn.hz.ddbm.setl.model.EtlTaskResponse;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;

import java.util.Map;

/**
 * 解析原子层xml，构建执行工作流
 */
public class AtomFactory extends BaseTaskFactory {

    @Override
    public EtlTaskResponse executeTask(EtlTaskRequest request) {
        return null;
    }

    @Override
    public Step dtoToDomain(EntryTaskstep dto, Task flow, Map<String, Action> actionMap) {
        return null;
    }


    @Override
    public String route(TaskRuntimeContext ctx) {
        return null;
    }

    @Override
    public String onException(TaskRuntimeContext ctx, Exception e)   {
        return null;
    }

    @Override
    public Map<String, Task> initWorkFlows() {
        return null;
    }
}
