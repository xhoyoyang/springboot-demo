package com.springboot.demo.config;


import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RedissonConfig {

    @Resource
    private RedisProperties redisProperties;


    /**
     * redis standalone config
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.model", havingValue = "standalone")
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort()).setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
