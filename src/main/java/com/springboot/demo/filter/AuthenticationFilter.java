package com.springboot.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.springboot.demo.common.constant.Media;
import com.springboot.demo.util.JwtUtil;
import com.springboot.demo.controller.auth.UserInfo;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationFilter extends GenericFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final static List<String> urls = Lists.newArrayList("/auth/**",
            //"/swagger-ui.html",
            //"/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/doc.html",
            "/webjars/**",
            "/favicon.ico");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException,MalformedJwtException {

        LOGGER.debug("do authentication");
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        boolean isCheck  = true;

        for (String url : urls) {
            if(new AntPathMatcher().match(url,StringUtils.replaceOnce(request.getRequestURI(),request.getContextPath(),""))){
                isCheck = false;
                break;
            }
        }

        if(isCheck){
            String token = request.getHeader(Media.MEDIA_TOKEN);
            // TODO: 2021/3/4 check token is not null and not exipred and effective
            if(StringUtils.isNoneBlank(token)){
                try {
                    LOGGER.debug("token is valid:{}",JwtUtil.isExpiration(token));
                    UserInfo userInfo = JwtUtil.parseToken(token);
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo,null,userInfo.getAuthorities()));
                }catch (Exception e){
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,false));
                }
            }else{
                LOGGER.debug("authentication is null");
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,false));
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
