package com.springboot.demo.controller.auth;

import com.springboot.demo.controller.request.UserLoginRequest;
import com.springboot.demo.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "用户认证")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(@RequestBody @Valid UserLoginRequest user) throws Exception {

        return this.authService.login(user);
    }

}
