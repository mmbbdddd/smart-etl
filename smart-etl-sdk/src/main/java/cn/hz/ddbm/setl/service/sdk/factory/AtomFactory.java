package cn.hz.ddbm.setl.service.sdk.factory;

import cn.hz.ddbm.setl.exception.EtlRouteException;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.EtlTaskstep;
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
    public Step dtoToDomain(EtlTaskstep dto, Task flow, Map<String, Action> actionMap) {
        return null;
    }


    @Override
    public String normalRoute(TaskRuntimeContext ctx) {
        return null;
    }

    @Override
    public String exceptionRoute(TaskRuntimeContext ctx, Exception e) throws EtlRouteException {
        return null;
    }

    @Override
    public Map<String, Task> initWorkFlows() {
        return null;
    }
}
