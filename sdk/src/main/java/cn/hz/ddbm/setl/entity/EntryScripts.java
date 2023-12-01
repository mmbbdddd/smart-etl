package cn.hz.ddbm.setl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (BaseScripts)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTRY_SCRIPTS")
public class EntryScripts implements Serializable {
    private static final long   serialVersionUID = -18164501183955966L;
    @TableId
    private              String scriptCode;
    private              String content;
    private              String app;
    private              String alias;
}

