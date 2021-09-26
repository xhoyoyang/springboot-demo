package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.controller.request.UserListRequest;
import com.springboot.demo.controller.request.UserRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.User;
import com.springboot.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        QueryWrapper<User> query = new QueryWrapper<>();
        query.orderByDesc("id");
        if(StringUtils.isNoneBlank(request.getUserName())){
            query.like("user_Mobile",request.getUserName());
        }
        if(StringUtils.isNoneBlank(request.getUserName())){
            query.like("user_Mobile",request.getUserMobile());
        }
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


    public void createUser(UserRequest request){

    }

    /**
     * 修改用户信息
     * @param request
     */
    @Transactional
    public void updateUser(UserRequest request){

        User user = new User();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setUserMobile(request.getUserMobile());
        user.setUserType(request.getUserTypeName());
        user.setUserEmail(request.getUserEmail());

        userMapper.update(user,null);

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
