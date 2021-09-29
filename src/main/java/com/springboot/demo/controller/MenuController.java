package com.springboot.demo.controller;

import com.springboot.demo.controller.request.MenuRequest;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.service.MenuService;
import com.springboot.demo.validate.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("查询菜单树")
    @GetMapping("/listTree")
    public Rs listTree(){

        return Rs.ok(this.menuService.listTree());
    }


    @ApiOperation("新增菜单")
    @GetMapping("/create")
    public Rs createMenu(@Validated({Update.class, Default.class}) @RequestBody MenuRequest request){

        this.menuService.createMenu(request);
        return Rs.ok();
    }

    @ApiOperation("修改菜单")
    @GetMapping("/update")
    public Rs updateMenu(@Validated({Update.class, Default.class}) @RequestBody MenuRequest request){

        this.menuService.updateMenu(request);
        return Rs.ok();
    }

    @ApiOperation("删除菜单")
    @GetMapping("/delete/{id}")
    public Rs deleteMenu(@PathVariable("id") Integer id){

        this.menuService.deleteMenu(id);
        return Rs.ok();
    }

}