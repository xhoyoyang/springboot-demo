package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.demo.util.JwtUtil;
import com.springboot.demo.controller.auth.UserInfo;
import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.DataNotExistException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public String login(UserLoginRequest request) throws Exception {

//        if(StringUtils.isBlank(user.getUsername())) throw new DataNotNullException("用户名不能为空");
//        if(StringUtils.isBlank(user.getPassword())) throw new DataNotNullException("密码不能为空");

        //验证用户名密码
        LambdaQueryWrapper<User> query = new QueryWrapper<User>().lambda();
        query.eq(User::getUserAccount,request.getUsername());
        //query.eq("user_password", DigestUtils.md5Hex(user.getPassword()));
        //先查询用户再验证密码，用于提高查询效率
        User user = userMapper.selectOne(query);
        if(null == user) throw new DataNotExistException("用户名或密码错误");
        if(!user.getUserPassword().equals(DigestUtils.md5Hex(request.getPassword()))) throw new DataNotExistException("用户名或密码错误");
        //查询用户角色权限
        Set<String> roles = roleMapper.findUserRoleMenuByUserId(user.getId());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        userInfo.setRoles(roles);
        //不需要的值
        userInfo.setCreateTime(null);
        userInfo.setUpdateTime(null);
        userInfo.setUserPassword(null);
        return JwtUtil.generateToken(userInfo);

    }
}
