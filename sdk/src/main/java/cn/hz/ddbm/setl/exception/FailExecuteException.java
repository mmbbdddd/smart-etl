package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;

public class FailExecuteException extends ExecuteException{
    public FailExecuteException(Step step, Action action,Exception e) {
        super(step, action,e);
    }
}
