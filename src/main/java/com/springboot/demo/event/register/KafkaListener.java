package com.springboot.demo.event.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by huiyang on 2022-07-08
 */
@Component
@Slf4j
public class KafkaListener {


    @org.springframework.kafka.annotation.KafkaListener(topics = "topic")
    public void msg(String msg){
        log.info("received kafka message ：{}",msg);
    }
}
