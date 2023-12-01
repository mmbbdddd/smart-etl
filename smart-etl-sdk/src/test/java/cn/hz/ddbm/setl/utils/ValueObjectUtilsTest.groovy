package cn.hz.ddbm.setl.utils

import cn.hz.ddbm.setl.entity.BaseDatasource

import org.junit.Assert
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValueObjectUtilsTest {
    @Autowired
    ApplicationContext ctx;
    //验证加载能力
    public void assertNotNull() {
        Assert.assertNotNull(ValueObjectUtils.findAll(ctx, BaseDatasource.class))
    }
    //验证校验能力
    public void assertValidate() {
    }
    //验证服务组装
    public void assertService() {
    }
    //验证刷新
    public void assertRefresh() {
    }
}
