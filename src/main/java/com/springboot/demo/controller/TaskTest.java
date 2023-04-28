package com.springboot.demo.controller;

import com.springboot.demo.common.result.Rs;
import com.springboot.demo.service.TaskTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhuiyang
 * @Description TODO
 * @Date 2022-03-30
 **/
@RestController
@RequestMapping("/task")
@Api("测试")
public class TaskTest {

    @Autowired
    private TaskTestService taskTestService;

    @ApiOperation("task1")
    @GetMapping("/task1")
    public Rs task1() throws InterruptedException {

        return Rs.ok(taskTestService.task1());
    }

    @ApiOperation("task2")
    @GetMapping("/task2")
    public Rs task2() throws InterruptedException {
        return Rs.ok(taskTestService.task2());
    }


}
