package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Step;

public class NotSupportCommandException extends Exception {
    public NotSupportCommandException(Step step,String command) {
    }

    public NotSupportCommandException(String s) {

    }
}
