package com.springboot.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.Method;
import java.util.Arrays;


@Configuration
@ConditionalOnBean(RedisConnectionFactory.class)
public class CacheConfig {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static final String detaultCache = "detaultCache";


    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public CacheManager redisCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(CacheConfig.detaultCache)));
        return cacheManager;
    }

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager simpleCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(CacheConfig.detaultCache)));
        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @SneakyThrows
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return o.getClass().getSimpleName() + ":" + method.getName() + ":" + MAPPER.writeValueAsString(objects);
            }
        };
    }

}
