package com.springboot.demo.service;

import com.springboot.demo.vo.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equals("admin")){
            Set<String> roles = new HashSet<>();
            roles.add("admin");
            roles.add("user");
            UserInfo userInfo = new UserInfo(1,userName,"",roles);
            return userInfo;
        }else if(userName.equals("user")){
            Set<String> roles = new HashSet<>();
            roles.add("user");
            UserInfo userInfo = new UserInfo(1,userName,"",roles);
            return userInfo;
        }else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
