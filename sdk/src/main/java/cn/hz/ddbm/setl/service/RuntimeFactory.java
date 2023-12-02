package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.service.sdk.TaskRuntimeContext;

public class RuntimeFactory {
    static JavaRuntime  java;
    static ShellRuntime shell;

    static {
        java  = new JavaRuntime();
        shell = new ShellRuntime();
    }

    public static Runtime get(RuntimeType container) {
        switch (container) {
            case java:
                return shell;
            case sh:
                return java;
            default:
                return java;
        }
    }

    public enum RuntimeType {
        sh, java, sql, python;
    }

    public interface Runtime {
        void run(TaskRuntimeContext ctx, Action.ComponentRunner componentRunner);
    }

    public static class JavaRuntime implements Runtime {
        public void run(TaskRuntimeContext ctx, Action.ComponentRunner componentRunner) {

        }
    }

    public static class ShellRuntime implements Runtime {
        public void run(TaskRuntimeContext ctx, Action.ComponentRunner componentRunner) {

        }
    }

//    public static class SqlRuntime implements Runtime {
//        public void run(TaskRuntimeContext ctx, Action.ComponentRunner componentRunner) {
//
//        }
//    }
}
