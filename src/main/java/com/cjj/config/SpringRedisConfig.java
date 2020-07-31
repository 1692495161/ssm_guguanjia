package com.cjj.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author cjj
 * @date 2020/7/28
 * @description
 */

/*
 * springdata-redis整合流程：
 * 1.引入springdata-redis和jedis依赖
 * 2.添加redis.properties配置文件，并读取到spring环境对象
 * 3.创建redis的连接工厂对象
 * 4.创建RedisTemplate对象
 * 5.需要使用redis缓存的功能类上注入RedisTemplate对象,操作对象api实现redis交互
 *
 * */
@Configuration
@PropertySource(value = "classpath:redis.properties",encoding = "utf-8")
public class SpringRedisConfig {

    @Bean
    public RedisConnectionFactory getConnectionFactory(@Value("${redis.host}")String host,
                                                       @Value("${redis.port}")int port,
                                                       @Value("${redis.password}")String password,
                                                       @Value("${redis.maxIdle}")int maxIdle){
        //创建一个以jedis方式连接的工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);

        //配置池连接配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);

        connectionFactory.setPoolConfig(poolConfig);

        return connectionFactory;
    }

    //创建RedisTemplate对象
    @Bean
    public RedisTemplate<Object,Object> getRedisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        //设置序列化器，解决序列化不乱码问题

        //设置  key的序列号器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        /*
        设置value的序列化器
        该序列化器会在将对象序列化成json字符串的时候，每个对象都添加类型说明
        需要被序列化的对象必须实现序列化接口
        */
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jsonRedisSerializer);

        //设置hash类型的序列化器(设置默认序列化，算是key和value通用的)
        redisTemplate.setDefaultSerializer(jsonRedisSerializer);

        return redisTemplate;
    }
}
