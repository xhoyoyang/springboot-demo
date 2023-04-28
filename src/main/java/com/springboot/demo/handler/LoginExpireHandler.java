package com.springboot.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.common.constant.Media;
import com.springboot.demo.common.result.Rs;
import com.springboot.demo.common.result.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginExpireHandler implements AuthenticationEntryPoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginExpireHandler.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        LOGGER.info("check authentication failed");
        Rs rs = null;
        if (e instanceof UsernameNotFoundException) {
            LOGGER.info("user not found");
            rs = Rs.error(RsStatus.LOGIN_FAIL);
            //httpServletResponse.setStatus(HttpStatus.PAYMENT_REQUIRED.value());//402
        } else if (e instanceof BadCredentialsException) {
            LOGGER.debug("Bad Credentials");
            rs = Rs.error(RsStatus.Bad_Credentials);
            //httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            rs = Rs.error(RsStatus.UNAUTHORIZED);
            //httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(Media.MEDIA_TYPE_JSON);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(MAPPER.writeValueAsString(rs));
        out.flush();
        out.close();
    }
}
