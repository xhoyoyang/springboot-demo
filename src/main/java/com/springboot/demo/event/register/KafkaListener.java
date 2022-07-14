package com.springboot.demo.event.register;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Created by huiyang on 2022-07-08
 */
@Component
@Slf4j
public class KafkaListener {


    @org.springframework.kafka.annotation.KafkaListener(topics = "topic")
    public void msg(ConsumerRecord<String,String> record, Acknowledgment acknowledgment){
        log.info("received kafka message ：{}:{}",record.key(),record.value());
        acknowledgment.acknowledge();
    }
}
