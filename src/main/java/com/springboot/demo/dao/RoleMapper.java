package com.springboot.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.demo.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 用户角色拥有的所有权限
     *
     * @param userId
     * @return
     */
    @Select("SELECT m.menu_url FROM user_role ur INNER JOIN role_menu rm on ur.role_id = rm.role_id INNER JOIN menu m on rm.menu_id = m.id  where ur.user_id = #{userId}")
    Set<String> findUserRoleMenuByUserId(Integer userId);

    /**
     * 用户拥有的所有角色
     * 角色名称
     *
     * @param userId
     * @return
     */
    @Select("select r.role_name,r.id from user_role u inner join role r on u.role_id = r.id where u.user_id = #{userId}")
    List<Role> findRolesByUserId(Integer userId);


    /**
     * 根据角色ID删除角色的所有权限
     *
     * @param roleId
     */
    @Delete("DELETE from role_menu where role_id = #{roleId}")
    void deletRoleMenuByRoleId(Integer roleId);


}
