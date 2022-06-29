package com.springboot.demo.init;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by huiyang on 2022-06-29
 */
@Component
@Slf4j
public class init implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {

        while (true){
            log.info(DateUtil.now());
            redisTemplate.opsForValue().set(UUID.fastUUID().toString(true), DateUtil.now());
            Thread.sleep(1000);
        }
    }
}
