package com.example.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate redisTemplate;



    public void xxx() {
        try {
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    Long size = connection.dbSize();
                    // Can cast to StringRedisConnection if using a StringRedisTemplate
                    return ((StringRedisConnection)connection).set("key", "value");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
