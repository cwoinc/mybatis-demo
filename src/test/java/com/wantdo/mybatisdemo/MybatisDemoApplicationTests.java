package com.wantdo.mybatisdemo;

import com.wantdo.mybatisdemo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {
    
    @Test
    public void contextLoads() {
    }
    
    @Resource
    private UserMapper userMapper;
    
    @Test
    public void test_db() {
        //userMapper.selectTwoList(null, null);
        userMapper.selectOneList(Arrays.asList());
        userMapper.list();
    }
    
}
