package com.springboot.demo.dao;

import com.springboot.demo.vo.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select user_info where name = #{usernmae}")
    UserInfo getUserByName(String usernmae);

}
