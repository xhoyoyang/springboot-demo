package com.springboot.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.Constant.Media;
import com.springboot.demo.handler.LoginExpireHandler;
import com.springboot.demo.handler.LoginSuccessHandler;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import com.springboot.demo.vo.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthenticationFilter extends GenericFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException,MalformedJwtException {

        LOGGER.debug("do authentication");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String url = "/auth/**";
        if(new AntPathMatcher().match(url,request.getRequestURI())){
            LOGGER.debug("this url is not check");
            filterChain.doFilter(servletRequest,servletResponse);
        }

        String token = request.getHeader(Media.MEDIA_TOKEN);
        // TODO: 2021/3/4 check token si not null and not exipred and effective
        if(StringUtils.isNoneBlank(token)){
            try {
                LOGGER.debug("token is expired:{}",JwtUtil.isExpiration(token));
                UserInfo userInfo = JwtUtil.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo,null,userInfo.getAuthorities()));
            }catch (JwtException e){
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,false));
            }
        }else{
            LOGGER.debug("authentication is null");
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,false));
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
