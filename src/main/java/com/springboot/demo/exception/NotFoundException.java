package com.springboot.demo.exception;

import com.springboot.demo.rs.Rs;
import com.springboot.demo.rs.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotFoundException implements ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(NotFoundException.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object notFound(){
        return Rs.error(RsStatus.NOT_FOUND);
    }
}
