package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.controller.request.UserListRequest;
import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.controller.request.UserUpdateRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.UserDo;
import com.springboot.demo.entity.UserInfo;
import com.springboot.demo.exception.DataNotExistException;
import com.springboot.demo.exception.DataNotNullException;
import com.springboot.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
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
        //查询用户角色权限
        Set<String> roles = roleMapper.findUserRoleMenuByUserId(userDo.getId());
        UserInfo userInfo = new UserInfo(userDo.getId(),userDo.getUserName(),null,roles);
        return JwtUtil.generateToken(userInfo);

    }

    /**
     * 分页查询用户信息
     * @param request
     * @return
     */
    public List<UserVo> listByPage(UserListRequest request){
        //
        QueryWrapper<UserDo> query = new QueryWrapper<>();
        query.orderByDesc("id");
        if(StringUtils.isNoneBlank(request.getUserName())){
            query.like("user_Mobile",request.getUserName());
        }
        if(StringUtils.isNoneBlank(request.getUserName())){
            query.like("user_Mobile",request.getUserMobile());
        }
        Page<UserDo> page = userMapper.selectPage(request.getPage(), query);
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

    public void updateUser(UserUpdateRequest request){
        UserDo userDo = new UserDo();
        userDo.setId(request.getId());
        userDo.setUserName(request.getUserName());
        userDo.setUserMobile(request.getUserMobile());
        userDo.setUserTypeName(request.getUserTypeName());
        userDo.setUserEmail(request.getUserEmail());

        userMapper.update(userDo,null);

    }



}
