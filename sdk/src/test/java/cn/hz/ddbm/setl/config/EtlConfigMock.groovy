package cn.hz.ddbm.setl.config

import org.junit.jupiter.api.BeforeAll
import org.mockito.Spy
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.spy


public class EtlConfigMock {

    @Spy
    NamedParameterJdbcTemplate jdbcTemplate;


    @BeforeAll
    public void mockJdbc(){
        spy(jdbcTemplate.query(anyString(),any(),any()))
    }
}