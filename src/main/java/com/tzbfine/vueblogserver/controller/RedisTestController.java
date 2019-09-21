package com.tzbfine.vueblogserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisTestController {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    //添加
    @GetMapping(value="/redisAdd")
    public void saveRedis(){
        redisTemplate.opsForValue().set("a","test");
    }

    //获取
    @GetMapping(value="/redisGet")
    public String getRedis(){
        return (String) redisTemplate.opsForValue().get("a");
    }
}