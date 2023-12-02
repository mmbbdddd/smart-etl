package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;

public class RouteExecuteException extends ExecuteException{
    public RouteExecuteException(Step step, Action action) {
        super(step, action);
    }
}
