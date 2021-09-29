package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springboot.demo.controller.request.RoleRequest;
import com.springboot.demo.dao.MenuMapper;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.RoleMenuMapper;
import com.springboot.demo.dao.UserRoleMapper;
import com.springboot.demo.entity.Menu;
import com.springboot.demo.entity.Role;
import com.springboot.demo.entity.RoleMenu;
import com.springboot.demo.entity.UserRole;
import com.springboot.demo.exception.DataNotExistException;
import com.springboot.demo.exception.DataNotNullException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 新增角色
     * @param request
     */
    @Transactional
    public void createRole(RoleRequest request){

        Role role = new Role();
        BeanUtils.copyProperties(request,role);
        this.roleMapper.insert(role);
        //赋予权限
        this.resetRoleMenu(request);
    }


    /**
     * 修改角色
     * @param request
     */
    @Transactional
    public void updateRole(RoleRequest request){

        //验证角色是否存在
        Role role = this.roleMapper.selectById(request.getId());
        if (null == role) throw new DataNotExistException();
        BeanUtils.copyProperties(request,role);
        this.roleMapper.updateById(role);
        //重新赋予权限
        this.resetRoleMenu(request);
    }

    /**
     * 删除角色
     * @param id
     */
    @Transactional
    public void deleteRole(Integer id){

        //验证角色是否存在
        Role role = this.roleMapper.selectById(id);
        if (null == role) throw new DataNotExistException();

        //验证角色是否正在使用
        Integer count = this.userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, id));
        if(count > 0) throw new DataNotNullException("该角色正在使用，不能被删除");

        this.roleMapper.deleteById(id);
    }

    /**
     * 角色赋权
     * @param request
     */
    public void resetRoleMenu(RoleRequest request){

        //验证权限是否存在
        List<Menu> menus = this.menuMapper.selectBatchIds(request.getMenus());
        if(menus.size() != request.getMenus().size()) throw new DataNotExistException();

        //删除以前的角色权限
        this.roleMapper.deletRoleMenuByRoleId(request.getId());

        // TODO-XHY: 2021/9/29 待优化，批量新增
        //重新赋值新的权限
        menus.forEach(menu -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(request.getId());
            roleMenu.setMenuId(menu.getId());
            this.roleMenuMapper.insert(roleMenu);
        });
    }

}
