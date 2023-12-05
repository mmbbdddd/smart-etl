package cn.hz.ddbm.setl.utils

import cn.hz.ddbm.setl.entity.EntryDatasource
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ValueObjectUtilsTest {
    @Autowired
    ApplicationContext ctx;
    //验证加载能力
    public void assertNotNull() {
        Assert.assertNotNull(ValueObjectUtils.findAll(ctx, EntryDatasource.class))
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
