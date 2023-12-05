package cn.hz.ddbm.setl.model;

import cn.hz.ddbm.setl.entity.TaskStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class EtlTaskStatus implements Serializable {
    String     taskId;
    TaskStatus flowStatus;
    String     flowStep;
    String     message;

}