package cn.hz.ddbm.setl.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import cn.hz.ddbm.setl.common.valueobject.VService;

import java.util.List;

public interface MybatisPlusAdapterToVService<T> extends VService<T>, BaseMapper<T> {

//    default Class<T> type() {
//        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 0);
//    }

    default T get(String code) {
        return selectById(code);
    }

    default List<T> findAll() {
        return selectList(new LambdaQueryWrapper<>());
    }
}
