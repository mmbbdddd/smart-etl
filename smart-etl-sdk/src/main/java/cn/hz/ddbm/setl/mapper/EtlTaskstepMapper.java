package cn.hz.ddbm.setl.mapper;

import cn.hz.ddbm.setl.config.MybatisPlusAdapterToVService;
import cn.hz.ddbm.setl.entity.EtlTaskstep;

/**
 * (EtlTaskstep)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-17 14:00:05
 */
public interface EtlTaskstepMapper extends MybatisPlusAdapterToVService<EtlTaskstep> {
    @Override
    default Class<EtlTaskstep> type() {
        return EtlTaskstep.class;
    }

}

