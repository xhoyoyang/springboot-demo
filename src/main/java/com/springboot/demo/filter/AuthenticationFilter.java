package com.springboot.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.Utils.JwtUtil;
import com.springboot.demo.Constant.Media;
import com.springboot.demo.handler.LoginExpireHandler;
import com.springboot.demo.handler.LoginSuccessHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.debug("do authoried");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String url = "/auth/**";

        if(new AntPathMatcher().match(url,request.getRequestURI())){
            LOGGER.debug("not check authentication");
            filterChain.doFilter(servletRequest,servletResponse);
        }

        String token = request.getHeader(Media.MEDIA_TOKEN);
        // TODO: 2021/3/4 check token si not null and not exipred and effective
        if(StringUtils.isNoneBlank(token)){
            try {
                LOGGER.debug("token is expired:{}",JwtUtil.isExpiration(token));
                UserInfo userInfo = JwtUtil.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo,null,userInfo.getAuthorities()));
            }catch (Exception e){
                filterChain.doFilter(servletRequest,servletResponse);
                //LOGGER.error(e.getMessage(),e);CredentialsExpireudException
                //throw new CredentialsExpiredException("token is expired");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
