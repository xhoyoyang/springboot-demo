package com.springboot.demo.controller;


import com.springboot.demo.controller.request.RoleListRequest;
import com.springboot.demo.controller.request.RoleRequest;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation("分页查询角色信息")
    @PostMapping("/listPage")
    public Rs listByPage(@Validated @RequestBody RoleListRequest request){

        this.roleService.listByPage(request);
        return Rs.ok(request.getPage());

    }


    @ApiOperation("新增角色")
    @PostMapping("/create")
    public Rs createRole(@Validated @RequestBody RoleRequest request){
        this.roleService.createRole(request);
        return Rs.ok();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public Rs updateRole(@Validated @RequestBody RoleRequest request){
        this.roleService.updateRole(request);
        return Rs.ok();
    }

    @ApiOperation("删除角色")
    @GetMapping("/delete/{id}")
    public Rs deleteRole(@PathVariable("id") Integer id){

        this.roleService.deleteRole(id);
        return Rs.ok();
    }
}
