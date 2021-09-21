package com.springboot.demo.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface RoleMapper {

    /**
     * 用户角色全权限
     * @param id
     * @return
     */
    @Select("SELECT m.menu_url FROM user_role ur INNER JOIN role_menu rm on ur.role_id = rm.role_id INNER JOIN menu m on rm.menu_id = m.id  where ur.user_id = #{id}")
    Set<String> findUserRoleMenuByUserId(Integer id);

    /**
     * 用户角色信息
     * @param userId
     * @return
     */
    @Select("select r.role_name from user_role u inner join role r on u.role_id = r.id where u.user_id = #{userId}")
    List<String> findRolesByUserId(Integer userId);
}
