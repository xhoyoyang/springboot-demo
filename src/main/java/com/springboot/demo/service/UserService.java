package com.springboot.demo.service;

import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.dao.UserDao;
import com.springboot.demo.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.zip.DataFormatException;

@Service
public class UserService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;

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

    public void login(UserLoginRequest user) throws Exception {

        UserInfo userInfo = this.userDao.getUserByName(user.getUsername());
        if(null == userInfo) throw new DataFormatException(String.format("用户【%s】不存在",user.getUsername()));

    }


}
