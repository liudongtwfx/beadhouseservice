package main.java.com.beadhouse.business.redisclient;

import redis.clients.jedis.Jedis;

public class RedisClientConnector {
    private static final Jedis redis = new Jedis("localhost");

    public static Jedis getRedis() {
        return redis;
    }
}
