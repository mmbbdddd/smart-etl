package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Step;
import lombok.Getter;

@Getter
public class NoActionForCommandException extends Exception {
    private String command;
    private Step   step;

    public NoActionForCommandException(String flowCode, Step step, String command) {
        super(String.format("no such action : %s.%s=>%s=>none", flowCode, step.getCode(), command));
        this.step    = step;
        this.command = command;
    }

    public NoActionForCommandException(String s) {

    }
}
