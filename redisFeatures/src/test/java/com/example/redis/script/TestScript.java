package com.example.redis.script;

import com.example.redis.utils.RedisHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestScript {

    @Autowired
    private RedisHelper redisHelper;

    @Test
    public void test01() {
        redisHelper.set("name", "scs");
    }

    @Test
    public void test02() {
        redisHelper.eval();
    }


}
