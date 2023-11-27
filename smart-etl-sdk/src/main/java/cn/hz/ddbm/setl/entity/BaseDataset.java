package cn.hz.ddbm.setl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (BaseDataset)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:11
 */
@Data
@TableName("BASE_DATASET")
public class BaseDataset implements Serializable {
    private static final long serialVersionUID = -29400266185905880L;

    private String datasetCode;
    private Integer cacheFlag;
    private String sql;
    private String datasourceCode;
    private              String app;
    private              String alias;




}

