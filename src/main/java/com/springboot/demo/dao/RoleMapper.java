package com.springboot.demo.dao;

import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RoleMapper {

    @Select("SELECT m.menu_url FROM user_role ur INNER JOIN role_menu rm on ur.role_id = rm.role_id INNER JOIN menu m on rm.menu_id = m.id  where ur.user_id = #{id}")
    Set<String> findUserRoleMenuByUserId(Integer id);
}
