package main.java.com.beadhouse.cache;

import com.google.gson.Gson;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import org.springframework.cache.Cache;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheManager {
    private Jedis jedis;

    public CacheManager() {
        jedis = RedisClientConnector.getLocalRedis();
    }

    public void addCache(CacheStruct cache) {
        addCache(cache, 0);
    }

    public void addCache(CacheStruct cache, int expiredsSeconds) {
        Gson gson = new Gson();
        try {
            jedis.zadd(cache.getKey(), cache.getScore(), gson.toJson(cache));
            if (expiredsSeconds != 0) {
                jedis.expire(cache.getKey(), expiredsSeconds);
            }
        } catch (Exception e) {

        }
    }

    public String visitCache(String key) {
        Set<String> v = jedis.zrange(key, 0, -1);
        Gson gson = new Gson();
        for (String s : v) {
            return gson.fromJson(s, CacheStruct.class).getValue();
        }
        return "";
    }

    public static long getUsedMemory(CacheSource source) {
        Jedis getUsedMemeryRedis = null;
        if (source == CacheSource.LOCAL) {
            getUsedMemeryRedis = RedisClientConnector.getLocalRedis();
        } else if (source == CacheSource.LAB_PC) {
            getUsedMemeryRedis = RedisClientConnector.getLabPCRedis();
        }
        if (getUsedMemeryRedis == null) {
            throw new IllegalStateException("get used memory exception");
        }
        return getMemory(getUsedMemeryRedis);
    }

    private static long getMemory(Jedis getUsedMemeryRedis) {
        String memoryInfos = getUsedMemeryRedis.info("Memory");
        for (String line : memoryInfos.split("\n")) {
            if (line.startsWith("used_memory")) {
                return Long.valueOf(line.split(":")[1]);
            }
        }
        return 10 * 1024 * 1024;
    }

    public static CacheStruct getCache(String key) {
        return null;
    }
}
