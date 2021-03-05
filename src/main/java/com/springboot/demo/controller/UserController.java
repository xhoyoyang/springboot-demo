package com.springboot.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/test1")
    public String test1(@NotNull(message = "name cant be null") String name){
        logger.debug("name is :{}",name);
        return "test1 ok";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "test2 ok";
    }
}
