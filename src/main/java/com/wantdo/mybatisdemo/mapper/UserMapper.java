package com.wantdo.mybatisdemo.mapper;

import com.wantdo.mybatisdemo.convert.SimpleSelectInLangDriver;
import com.wantdo.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author king
 */
public interface UserMapper {
    
    @Select("select * from t_user ")
    List<User> list();
    
    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);
    
    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);
    
    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);
    
    @Select("SELECT * FROM t_user WHERE id IN (#{list})")
    @Lang(SimpleSelectInLangDriver.class)
    List<User> selectRolesByRoleId(List<Integer> roleIdList);
    
    //@Select("SELECT * FROM t_user WHERE id IN <foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>#{item}</foreach>")
    //@Select("SELECT * FROM t_user WHERE id IN <foreach collection=\"list\" item=\"_item\" open=\"(\" separator=\",\" close=\")\" >#{_item}</foreach>")
    //@Select("SELECT * " +
    //        "FROM t_user " +
    //        "WHERE id IN " +
    //        "<foreach item='item' index='index' collection='list'open='(' separator=',' close=')'>" +
    //        "#{item}" +
    //        "</foreach>")
    //List<User> selectUsersByRoleIdList(List<Integer> roleIdList);
    
}