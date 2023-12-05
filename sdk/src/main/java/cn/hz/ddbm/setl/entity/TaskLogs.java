package cn.hz.ddbm.setl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import cn.hz.ddbm.setl.domain.TaskType;
import lombok.Data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (EntityEtlTaskLogs)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTITY_ETL_TASK_LOGS")
public class TaskLogs implements Serializable {
    private static final long       serialVersionUID = -94172125099455904L;
    @TableId
    private              Long       id;
    private String     taskCode;
    private TaskType   engineType;
    private TaskStatus taskStatus;
    private              String     taskStep;
    private              String     stepStatus;


}

