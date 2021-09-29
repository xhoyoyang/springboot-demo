package com.springboot.demo.handler;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorityHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityHandler.class);

    public boolean hasPermission(HttpServletRequest request ,Authentication authentication){

        LOGGER.debug("check permission");
        boolean isAllowed = false ;

        Object userInfo = authentication.getPrincipal();

        if(userInfo instanceof UserDetails) {
            Set<String> urls = new HashSet<>();
            // TODO: 2021/3/4 you have to set permission url with users
            List<? extends GrantedAuthority> authorities = ((UserDetails) userInfo).getAuthorities().stream().collect(Collectors.toList());

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            LOGGER.info("authorities:{}",authentication.getAuthorities());


            //验证权限
            for (GrantedAuthority item : authorities) {
                if(new AntPathMatcher().match(item.toString(),StringUtils.replaceOnce(request.getRequestURI(),request.getContextPath(),""))){
                    isAllowed = true;
                }
            }
            //for 2
            /*for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    isAllowed = true;
                    break;
                }
            }*/

        }

        return  isAllowed;
    }




}
