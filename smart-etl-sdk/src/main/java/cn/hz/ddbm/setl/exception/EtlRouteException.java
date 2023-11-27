package cn.hz.ddbm.setl.exception;

import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import lombok.Getter;

@Getter
public class EtlRouteException extends Exception {
    String cmd;
    Step       step;
    Action     action;
}
