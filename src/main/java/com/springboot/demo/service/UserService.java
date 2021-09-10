package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.UserDo;
import com.springboot.demo.exception.DataNotExistException;
import com.springboot.demo.exception.DataNotNullException;
import com.springboot.demo.vo.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equals("admin")){
            Set<String> roles = new HashSet<>();
            roles.add("/user/test1");
            roles.add("/user/test2");
            UserInfo userInfo = new UserInfo(1,userName,"",roles);
            return userInfo;
        }else if(userName.equals("user")){
            Set<String> roles = new HashSet<>();
            roles.add("/user/test1");
            UserInfo userInfo = new UserInfo(1,userName,"",roles);
            return userInfo;
        }else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    public String login(UserLoginRequest user) throws Exception {

        if(StringUtils.isBlank(user.getUsername())) throw new DataNotNullException("用户名不能为空");
        if(StringUtils.isBlank(user.getPassword())) throw new DataNotNullException("密码不能为空");

        //验证用户名密码
        QueryWrapper<UserDo> query = new QueryWrapper<>();
        query.eq("user_account",user.getUsername());
        query.eq("user_password", DigestUtils.md5Hex(user.getPassword()));
        UserDo userDo = userMapper.selectOne(query);
        if(null == userDo) throw new DataNotExistException("用户名或密码错误");
        //查询用户角色
        Set<String> roles = roleMapper.findUserRoleMenuByUserId(userDo.getId());
        UserInfo userInfo = new UserInfo(userDo.getId(),userDo.getUserName(),null,roles);
        return JwtUtil.generateToken(userInfo);

    }

    public List<UserDo> listByPage(){
        //
        QueryWrapper<UserDo> query = new QueryWrapper<>();
        query.orderByDesc("id");
        Page<UserDo> page = userMapper.selectPage(new Page<>(30000,10), query);
        return page.getRecords();
    }


}
