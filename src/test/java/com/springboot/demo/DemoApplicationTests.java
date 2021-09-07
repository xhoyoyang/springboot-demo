package com.springboot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        System.out.println(new Date());
    }

    @Test
    void redisTest(){
        this.redisTemplate.opsForValue().set("test","test", Duration.ofMinutes(10L));
    }

}
