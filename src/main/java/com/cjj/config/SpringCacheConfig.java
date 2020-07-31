package com.cjj.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjj
 * @date 2020/7/28
 * @description
 */
/*
 * spring的缓存框架配置类，开发流程：
 * 1.配置spring的缓存管理器对象，设置缓存管理器对象中管理的缓存名字
 * 2.开启缓存注解支持
 * 3.在需要使用缓存的类或者方法上添加缓存注解，设置key和对应的缓存名字
 * */

//缓存配置类
@Configuration
//开启缓存注解支持
@EnableCaching
public class SpringCacheConfig {

    //RedisOperations:redisTemplate的父接口，spring会自动注入名为redisTemplate的对象
    @Bean
    public CacheManager getCacheManager(RedisOperations operations){
        RedisCacheManager redisCacheManager = new RedisCacheManager(operations);
        //创建  缓存对象名  的list集合，spring会根据对应名字创建对应缓存对象
        List<String> cacheNames = new ArrayList<>();
        cacheNames.add("officeCache");
        cacheNames.add("orderCache");
        //设置缓存管理器对象中管理的缓存名字
        redisCacheManager.setCacheNames(cacheNames);
        //设置缓存默认的生存时间（秒）
        redisCacheManager.setDefaultExpiration(600);
        return redisCacheManager;
    }
}
