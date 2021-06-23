package com.rj.bd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration//这个注解的作用是将来项目启动的时候，自动的吧当前这个类加载到spring中
public class ConfigRedis {

	
@Autowired
private RedisTemplate redisTemplate;//自动的装配一个redisTemplate


/**
 * @description: TODO 类描述
 * @author: zc
 * @date: 2021/5/30
 **/
@SuppressWarnings({ "unchecked", "rawtypes" })
@Bean
public RedisTemplate redisTemplateInit() {
	System.out.println("redis存入数据的时候实现序列化");
    //设置序列化Key的实例化对象
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //设置序列化Value的实例化对象
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
}



	
}
