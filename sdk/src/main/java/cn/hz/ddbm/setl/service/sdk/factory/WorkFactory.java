package cn.hz.ddbm.setl.service.sdk.factory;

import cn.hz.ddbm.setl.exception.EtlRouteException;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.entity.EtlTaskstep;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;

import java.util.Map;

/**
 * 解析工作流定义
 */
public class WorkFactory extends BaseTaskFactory {

    @Override
    public Map<String, Task> initWorkFlows() {
        //从数据库定义中创建流程定义

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
}
