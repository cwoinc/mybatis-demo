package com.wantdo.mybatisdemo.mapper;

import com.wantdo.mybatisdemo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 数据层代码如下：
public interface UserMapper {

    @Select("select * from t_user where 1=1")
    List<User> list();

    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);

    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);
}