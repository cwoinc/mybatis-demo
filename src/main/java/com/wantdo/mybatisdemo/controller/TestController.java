package com.wantdo.mybatisdemo.controller;

import com.wantdo.mybatisdemo.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author king
 * @version 2018-08-09 8:00 PM
 */
@RestController
public class TestController {
    
    @Resource
    private UserMapper userMapper;
    
    @RequestMapping("/test")
    public String test() {
        return "fdgh";
    }
    
    @RequestMapping("/user")
    public Object user() {
        return userMapper.selectRolesByRoleId(Arrays.asList(1,2));
        //return userMapper.selectUsersByRoleIdList(Arrays.asList(1, 2));
    }
    
}
