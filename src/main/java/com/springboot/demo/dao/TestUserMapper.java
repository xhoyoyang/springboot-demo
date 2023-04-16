package com.springboot.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.entity.TestUser;
import com.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface TestUserMapper extends BaseMapper<TestUser> {


    @Select("SELECT count(*) from test_user")
    int count();

}
