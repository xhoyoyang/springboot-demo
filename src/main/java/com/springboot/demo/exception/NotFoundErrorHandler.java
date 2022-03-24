package com.springboot.demo.exception;

import com.springboot.demo.common.result.Rs;
import com.springboot.demo.common.result.RsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Configuration
public class NotFoundErrorHandler {

    private final static Logger logger = LoggerFactory.getLogger(NotFoundErrorHandler.class);

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return  (factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            factory.addErrorPages(errorPage404);
        });
    }


    @GetMapping("/404")
    public Rs notFound(){

        return Rs.error(RsStatus.NOT_FOUND);
    }

}
