package com.springboot.demo.controller;

import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.service.UserService;
import com.springboot.demo.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest user){
        UserInfo userInfo = (UserInfo) userService.loadUserByUsername(user.getUsername());
        return JwtUtil.generateToken(userInfo);
    }

}
