package cn.hz.ddbm.setl.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (EntityEtlTaskRequestLogs)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTITY_ETL_TASK_REQUEST_LOGS")
public class TaskRequestLogs implements Serializable {
    private static final long    serialVersionUID = 211196421955231756L;
    @TableId
    private              Integer id;

    private String taskCode;

    private String taskStep;

    private String command;

    private String args;

    private Date createTime;

    private Date modifyTime;


}

