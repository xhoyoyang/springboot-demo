package com.springboot.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.common.constant.Media;
import com.springboot.demo.common.result.Rs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        LOGGER.debug("login success");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(Media.MEDIA_TYPE_JSON);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(MAPPER.writeValueAsString(Rs.ok()));
        out.flush();
        out.close();
    }
}
