package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Utils.AuthorizationUtil;
import com.springboot.demo.controller.request.UserListRequest;
import com.springboot.demo.controller.request.UserRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.dao.UserRoleMapper;
import com.springboot.demo.entity.Role;
import com.springboot.demo.entity.User;
import com.springboot.demo.entity.UserRole;
import com.springboot.demo.exception.DataExistException;
import com.springboot.demo.exception.DataNotExistException;
import com.springboot.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    /**
     * 分页查询用户信息
     * @param request
     * @return
     */
    public List<UserVo> listByPage(UserListRequest request){
        //
        LambdaQueryWrapper<User> query = new QueryWrapper<User>().lambda();
        query.like(User::getUserName, request.getUserName());

        Page<User> page = userMapper.selectPage(request.getPage(), query);
        List<UserVo> users = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(item->{
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(item,userVo);
            //用户角色
            List<String> roles = this.roleMapper.findRolesByUserId(userVo.getId());
            userVo.setRoles(roles);
            users.add(userVo);
        });
        return users;
    }


    @Transactional
    public void createUser(UserRequest request){

        User user = new User();
        BeanUtils.copyProperties(request,user);
        user.buildForCreate();
        //验证用户名
        List<User> users = this.userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getUserAccount, user.getUserAccount()));
        if(!CollectionUtils.isEmpty(users)) throw new DataExistException("用户名已存在");
        //验证角色
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().in("id",request.getRoles()));
        if (request.getRoles().size() != roles.size()) throw new DataNotExistException("角色不存在,请检查参数");
        //新增用户
        this.userMapper.insert(user);
        //新增用户角色
        //List<UserRole> userRoles = new ArrayList<>(roles.size());
        roles.forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            this.userRoleMapper.insert(userRole);
        });

    }

    /**
     * 修改用户信息
     * @param request
     */
    @Transactional
    public void updateUser(UserRequest request){

        //验证用户是否存在
        User user = this.userMapper.selectById(request.getId());
        if(null == user) throw new DataNotExistException("用户不存在");
        //验证角色
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().lambda().in(Role::getId,request.getRoles()));
        if (request.getRoles().size() != roles.size()) throw new DataNotExistException("角色不存在,请检查参数");
        BeanUtils.copyProperties(request,user);
        user.buildForUpdatee();
        //更新用户
        this.userMapper.updateById(user);
        //更新用户角色，先删除用户所有角色
        this.userRoleMapper.delete(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,user.getId()));
        roles.forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            this.userRoleMapper.insert(userRole);
        });
        AuthorizationUtil.currentUser();

    }

    /**
     * 删除用户
     * @param userId
     */
    @Transactional
    public void deleteUser(Integer userId){
        this.userMapper.deleteById(userId);
    }



}
