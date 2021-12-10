package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.demo.controller.auth.UserInfo;
import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.User;
import com.springboot.demo.exception.defination.DataNotExistException;
import com.springboot.demo.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public String login(UserLoginRequest request) throws Exception {

        LambdaQueryWrapper<User> query = new QueryWrapper<User>().lambda();
        query.eq(User::getUserAccount, request.getUsername());
        //query.eq("user_password", DigestUtils.md5Hex(user.getPassword()));
        //先查询用户再验证密码，用于提高查询效率
        User user = userMapper.selectOne(query);
        if (null == user) {
            throw new DataNotExistException("用户名或密码错误");
        }
        if (!user.getUserPassword().equals(DigestUtils.md5Hex(request.getPassword()))) {
            throw new DataNotExistException("用户名或密码错误");
        }
        UserInfo userInfo = this.getUserInfo(user.getId());
        return JwtUtil.generateToken(userInfo);

    }


    public UserInfo getUserInfo(Integer userId){

        User user = userMapper.selectById(userId);
        //查询用户角色权限
        Set<String> roles = roleMapper.findUserRoleMenuByUserId(user.getId());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        userInfo.setRoles(roles);
        //  用户信息存入缓存
        this.redisTemplate.opsForValue().set("auth:user:id:"+user.getId(),userInfo, Duration.ofDays(1));
        return userInfo;
    }
}
