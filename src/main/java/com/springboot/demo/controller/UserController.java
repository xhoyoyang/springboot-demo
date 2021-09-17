package com.springboot.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.controller.request.UserListRequest;
import com.springboot.demo.controller.request.UserUpdateRequest;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.service.UserService;
import com.springboot.demo.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("分页查询用户信息")
    @PostMapping("/list")
    public Rs<Page<UserVo>> list(@Validated @RequestBody UserListRequest request){
        Page page = request.getPage();
        page.setRecords(userService.listByPage(request));
        return Rs.ok(page);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public Rs updateUser(@Validated @RequestBody UserUpdateRequest request){

        return Rs.ok();
    }
}
