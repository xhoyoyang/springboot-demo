package com.springboot.demo.ws.listener;

import com.springboot.demo.ws.event.WebScoketEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by huiyang on 2022-07-21
 */
@Component
@Slf4j
public class WebSocketKafkaListener {

    @EventListener
    public void listener(WebScoketEvent event){
        log.info("WebSocketKafkaListener received message：{}",event.getMsg());
    }
}
