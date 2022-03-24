package com.springboot.demo.event.register;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsListener {

    @EventListener
    public void sendSms(UserRegisterEvent event){

        log.info("用户[{}]注册成功，发送短信",event.getUserName());
    }
}
