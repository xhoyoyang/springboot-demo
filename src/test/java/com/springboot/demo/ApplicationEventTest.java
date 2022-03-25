package com.springboot.demo;


import com.springboot.demo.event.register.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootTest(classes = Application.class)
@ComponentScan("com.springboot.demo")
@Component
public class ApplicationEventTest {


    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void register() throws InterruptedException {
        log.info("用户注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, "张三"));
    }


}
