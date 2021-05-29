package com.example.redis.utils;

import io.lettuce.core.internal.LettuceLists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;


@Component
public class RedisHelper {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

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

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void eval(){
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();

        redisScript.setScriptText("return redis.call('client id')");
        // 指定 lua 脚本
        //redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/DelKey.lua")));
        // 指定返回类型
        redisScript.setResultType(Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = redisTemplate.execute(redisScript, LettuceLists.newList());
        System.out.println(result);
    }

}
