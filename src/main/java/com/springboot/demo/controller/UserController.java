package com.springboot.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/test1")
    public String test1(){
        return "test1 ok";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "test2 ok";
    }
}
