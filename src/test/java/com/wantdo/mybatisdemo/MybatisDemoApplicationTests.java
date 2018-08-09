package com.wantdo.mybatisdemo;

import com.wantdo.mybatisdemo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

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
        userMapper.selectRolesByRoleId(Arrays.asList());
    }

}
