package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExecuteException extends Exception {
    Step   step;
    Action action;

    public ExecuteException(Step step, Action action, Exception e) {
        super(e);
        this.step   = step;
        this.action = action;
    }


}
