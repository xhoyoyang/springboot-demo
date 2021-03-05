package com.springboot.demo.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorityHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityHandler.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public boolean hasPermission(HttpServletRequest request ,Authentication authentication){

        LOGGER.debug("check permission");
        boolean isAllowed = false ;

        Object userInfo = authentication.getPrincipal();

        if(userInfo instanceof UserDetails) {
            Set<String> urls = new HashSet<>();
            // TODO: 2021/3/4 you have to set permission url with users 
            urls.add("/**");

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            LOGGER.info("authorities:{}",authentication.getAuthorities());

            if(((UserDetails) userInfo).getAuthorities().contains(new SimpleGrantedAuthority(request.getRequestURI()))){
                isAllowed = true;
            }

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
