package com.springboot.demo.controller;

import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.service.UserService;
import com.springboot.demo.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String userName){
        UserInfo userInfo = (UserInfo) userService.loadUserByUsername(userName);
        return JwtUtil.generateToken(userInfo);
    }

}
