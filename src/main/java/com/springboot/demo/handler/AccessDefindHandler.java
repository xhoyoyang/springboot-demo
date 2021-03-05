package com.springboot.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.Constant.Media;
import com.springboot.demo.rs.Rs;
import com.springboot.demo.rs.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessDefindHandler implements AccessDeniedHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityHandler.class);
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

       LOGGER.debug("access denied");
        Map<String,Object> map = new HashMap<>();
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(Media.MEDIA_TYPE_JSON);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(MAPPER.writeValueAsString(Rs.error(RsStatus.FORBIDDEN)));
        out.flush();
        out.close();

    }
}
