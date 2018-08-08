package com.wantdo.mybatisdemo;

import com.wantdo.mybatisdemo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    @Test
    public void test_db() {
        System.out.println(userMapper.list().size());
        //开始进行测试
        assertThat(userMapper.list().size()).isGreaterThan(1);
        assertThat(userMapper.getOne("1")).isNotEqualTo(null);
        assertThat(userMapper.getOne("xxx")).isEqualTo(null);
    }

}
