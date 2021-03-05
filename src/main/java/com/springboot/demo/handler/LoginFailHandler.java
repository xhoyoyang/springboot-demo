package com.springboot.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.Constant.Media;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.rs.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginFailHandler implements AuthenticationFailureHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginFailHandler.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        LOGGER.debug("login fail");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(Media.MEDIA_TYPE_JSON);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(MAPPER.writeValueAsString(Rs.error(RsStatus.LOGIN_FAIL)));
        out.flush();
        out.close();
    }
}
