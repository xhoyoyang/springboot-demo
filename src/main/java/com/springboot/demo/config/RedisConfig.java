package com.springboot.demo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * lettuce connection pool
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.lettuce.pool.max-active")
    public GenericObjectPoolConfig genericObjectPoolConfig(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        config.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        config.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        config.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        config.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());

        return config;
    }

    /**
     * Redis Sentinel Configuration
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.sentinel.master")
    public RedisSentinelConfiguration redisSentinelConnection(){
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();

        config.setMaster(redisProperties.getSentinel().getMaster());
        List<RedisNode> redisNodes = new ArrayList<>();

        redisProperties.getSentinel().getNodes().forEach(item->{
            redisNodes.add(new RedisNode(item.split(":")[0],Integer.parseInt(item.split(":")[1])));
        });
        config.setSentinels(redisNodes);
        config.setPassword(RedisPassword.of(redisProperties.getSentinel().getPassword()));

        return config;

    }

    /**
     * Redis Standalone Configuration
     * @return
     */
    @ConditionalOnProperty(name = "spring.redis.host")
    @ConditionalOnMissingBean(RedisSentinelConfiguration.class)
    @Bean
    public RedisConfiguration redisConfiguration(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        configuration.setDatabase(redisProperties.getDatabase());

        return configuration;
    }


    @Bean
    @ConditionalOnBean({GenericObjectPoolConfig.class,RedisConfiguration.class})
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig poolConfig , RedisConfiguration redisConfiguration){
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration= LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration,lettucePoolingClientConfiguration);

        return lettuceConnectionFactory;

    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        StringRedisTemplate redisTemplate = new StringRedisTemplate();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);

        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){

        RedisTemplate redisTemplate = new StringRedisTemplate();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }
}
