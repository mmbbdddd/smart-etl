package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;

public class RuntimeContainerFactory {
     public static Runtime get(RuntimeType container){
         //TODO
         return null;
     }

    public enum RuntimeType {
        sh, java, sql, python;
    }

    public interface Runtime {
        void run(TaskRuntimeContext ctx, Action.ComponentRunner componentRunner);
    }
}
