package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;

public interface Component {
    void execute(TaskRuntimeContext ctx) throws Exception;
}
