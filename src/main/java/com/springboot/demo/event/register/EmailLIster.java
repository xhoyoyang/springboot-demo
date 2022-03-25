package com.springboot.demo.event.register;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailLIster {

    @EventListener
    public void sendEmail(UserRegisterEvent event) {
        log.info("用户[{}]注册成功，发送邮件", event.getUserName());
    }
}
