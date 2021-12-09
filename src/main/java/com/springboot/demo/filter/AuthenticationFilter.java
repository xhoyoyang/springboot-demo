package com.springboot.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.springboot.demo.common.constant.Media;
import com.springboot.demo.controller.auth.UserInfo;
import com.springboot.demo.service.AuthService;
import com.springboot.demo.util.JwtUtil;
import com.springboot.demo.util.SpringUtil;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class AuthenticationFilter extends GenericFilter {

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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, MalformedJwtException {

        log.debug("do authentication");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        boolean isCheck = true;

        for (String url : urls) {
            if (new AntPathMatcher().match(url, StringUtils.replaceOnce(request.getRequestURI(), request.getContextPath(), ""))) {
                isCheck = false;
                break;
            }
        }

        if (isCheck) {
            String token = request.getHeader(Media.MEDIA_TOKEN);
            // TODO: 2021/3/4 check token is not null and not exipred and effective
            if (StringUtils.isNoneBlank(token)) {
                try {
                    log.debug("token is valid:{}", JwtUtil.isExpiration(token));

                    AuthService authService = SpringUtil.getBean(AuthService.class);
                    RedisTemplate<String,Object> redisTemplate = (RedisTemplate)SpringUtil.getBean("redisTemplate");

                    //先从缓存里拿用户信息，拿不到再去查库
                    UserInfo userInfo = JwtUtil.parseToken(token);
                    Object o = null;
                    try {
                         o = redisTemplate.opsForValue().get("auth:user:id:" + userInfo.getId());
                    } catch (Exception e) {
                        log.error("get catch error");
                    }
                    if (null == o) {
                        userInfo = authService.getUserInfo(userInfo.getId());
                    } else {
                        userInfo = (UserInfo) o;
                    }
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities()));
                } catch (Exception e) {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, false));
                }
            } else {
                log.debug("authentication is null");
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, false));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
