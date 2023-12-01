package cn.hz.ddbm.setl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (BaseDatasource)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTRY_DATASOURCE")
public class EntryDatasource implements Serializable {
    private static final long serialVersionUID = -33889968870694021L;
    @TableId
    private String datasourceCode;
    private Object driverClassName;
    private Object url;
    private String username;
    private String passowrd;
    private String attrsJson;
    private String type;
    private Integer threshold;
    private String app;
    private String alias;
}

