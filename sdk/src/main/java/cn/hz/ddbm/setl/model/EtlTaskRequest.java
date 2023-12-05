package cn.hz.ddbm.setl.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class EtlTaskRequest implements Serializable {
    String              taskId;
    String              taskCode;
    String              command;
    Map<String, Object> args;
    Boolean             fluent;
}