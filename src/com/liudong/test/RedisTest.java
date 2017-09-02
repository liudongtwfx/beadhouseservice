package com.liudong.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2017/4/26.
 */

public class RedisTest {

    private final Jedis redis = new Jedis("localhost");

    @Test
    public void putKeyValue() {
        Jedis jedis = new Jedis("localhost");
        Map<String, String> user = new HashMap<>();
        user.put("password", "liu@6820138");
        user.put("school", "bupt");
        jedis.del("liudong");
        if (jedis.get("liduong") == null) {
            jedis.hmset("liudong", user);
        }
    }

    @Test
    public void getKeyValue() {
        List<String> list = redis.hmget("liudong", "password", "school");
        for (String l : list) {
            System.out.println(l);
        }
    }
}
