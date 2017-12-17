package main.java.com.beadhouse.business.redisclient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClientConnector {
    private static final Jedis redis = new Jedis("localhost");

    public static Jedis getRedis() {

        return redis;
    }

    private static final JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMinIdle(20);
        config.setMaxWaitMillis(10000);
        pool = new JedisPool(config, "39.106.165.69", 6379);
    }

    public static Jedis getAliyunRedis() {
        return pool.getResource();
    }
}
