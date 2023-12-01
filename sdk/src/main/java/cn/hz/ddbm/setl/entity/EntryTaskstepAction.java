package cn.hz.ddbm.setl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Task;
import lombok.Data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.context.ApplicationContext;

/**
 * (EtlTaskstepAction)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTRY_TASKSTEP_ACTION")
public class EntryTaskstepAction implements Serializable     {
    private static final long    serialVersionUID = 641312056809409315L;
    @TableId
    private              Integer id;
    private              String  action;
    private              String  name;
    private              String  taskCode;
    private              String  stepCode;
    private              String  command;
    private              String  attrs;
    private              String  memo;
    private              String app;
    private              String alias;


    public static Action build(Task flow, EntryTaskstepAction dto, ApplicationContext ctx) throws Throwable {
        return new Action(dto.getAction(), dto.getName());
    }

}

