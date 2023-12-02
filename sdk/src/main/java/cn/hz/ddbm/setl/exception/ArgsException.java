package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Step;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ArgsException extends RuntimeException {
    private String task;
    private String step;
    private String command;

    public ArgsException(String task, String step, String command) {
        this.task    = task;
        this.step    = step;
        this.command = command;
    }

    public static ArgsException notSupportFunction(String s) {
        return null;
    }


    public static ArgsException noActionForCommand(String code, Step step, String command) {
        return null;
    }
}
