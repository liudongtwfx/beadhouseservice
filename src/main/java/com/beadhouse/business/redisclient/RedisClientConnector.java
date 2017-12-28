package main.java.com.beadhouse.business.redisclient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClientConnector {
    private static final JedisPool localhostpool;

    public static Jedis getLocalRedis() {
        return localhostpool.getResource();
    }

    private static final JedisPool labpcpool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMinIdle(20);
        config.setMaxWaitMillis(10000);
        labpcpool = new JedisPool(config, "10.103.249.28", 6379);
        localhostpool = new JedisPool(config, "localhost", 6379);
    }

    public static Jedis getLabPCRedis() {
        return labpcpool.getResource();
    }

    public static Jedis getRedis() {
        return localhostpool.getResource();
    }
}
