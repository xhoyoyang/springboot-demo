package com.springboot.demo.util;

import com.springboot.demo.controller.auth.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationUtil {

    public static UserInfo currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserInfo){
            return (UserInfo) principal;
        }
        return null;
    }

}
