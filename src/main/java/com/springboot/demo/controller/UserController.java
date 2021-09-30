package com.springboot.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.controller.request.UserListRequest;
import com.springboot.demo.controller.request.UserRequest;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.service.UserService;
import com.springboot.demo.validate.group.Create;
import com.springboot.demo.validate.group.Update;
import com.springboot.demo.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("分页查询用户信息")
    @PostMapping("/listPage")
    public Rs<Page<UserVo>> list(@Validated @RequestBody UserListRequest request){
        this.userService.listByPage(request);
        return Rs.ok(request.getPage());
    }

    @ApiOperation("新增用户")
    @PostMapping("/create")
    public Rs createUser(@Validated(Create.class) @RequestBody UserRequest request){
        this.userService.createUser(request);
        return Rs.ok();
    }


    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    @Validated
    public Rs updateUser(@RequestBody @Validated(Update.class) UserRequest request){
        this.userService.updateUser(request);
        return Rs.ok();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation("删除用户")
    @GetMapping("/delete/{id}")
    public Rs deleteUser(@PathVariable("id") Integer id){
        this.userService.deleteUser(id);
        return Rs.ok();
    }
}
