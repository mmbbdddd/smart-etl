package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EtlStepException extends Exception {
    String cmd;
    Step   step;
    Action action;

}
