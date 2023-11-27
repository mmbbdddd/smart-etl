package cn.hz.ddbm.setl.mapper;

import cn.hz.ddbm.setl.config.MybatisPlusAdapterToVService;
import cn.hz.ddbm.setl.entity.BaseScripts;

/**
 * (BaseScripts)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-17 14:00:06
 */
public interface BaseScriptsMapper extends MybatisPlusAdapterToVService<BaseScripts> {
    @Override
    default Class<BaseScripts> type() {
        return BaseScripts.class;
    }
}

