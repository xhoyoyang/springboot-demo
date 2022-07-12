package com.springboot.demo.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by huiyang on 2022-06-29
 */
@Component
@Slf4j
public class init implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {

       /* while (true){

            //redisTemplate.opsForValue().set(UUID.fastUUID().toString(true), DateUtil.now());
            this.kafkaTemplate.send("topic", UUID.fastUUID().toString(true));
            Thread.sleep(100);
        }*/
    }

}
