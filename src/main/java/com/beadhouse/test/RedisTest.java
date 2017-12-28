package main.java.com.beadhouse.test;

import com.google.gson.Gson;
import main.java.com.beadhouse.cache.CacheManager;
import main.java.com.beadhouse.cache.CacheStruct;
import org.junit.Test;
import org.springframework.cache.Cache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;

import java.util.*;

/**
 * Created by beadhouse on 2017/4/26.
 */

public class RedisTest {

    private final Jedis redis = new Jedis("localhost");

    @Test
    public void putKeyValue() {
        Jedis jedis = new Jedis("localhost");
        Map<String, String> user = new HashMap<>();
        user.put("password", "liu@6820138");
        user.put("school", "bupt");
        jedis.del("beadhouse");
        if (jedis.get("liduong") == null) {
            jedis.hmset("beadhouse", user);
        }
    }

    @Test
    public void getKeyValue() {
        List<String> list = redis.hmget("beadhouse", "password", "school");
        for (String l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void zaddTest() {
        Set<Tuple> set = redis.zrangeWithScores("zset-key", 0, -1);
        for (Tuple tuple : set) {
            System.out.println(tuple.getElement() + " " + tuple.getScore());
        }
        //redis.zadd("zadd-key", 455, "number1");
    }

    @Test
    public void addRoles() {
        Jedis jedis = new Jedis("localhost");
        redis.auth("liu@6820138");
        //jedis.zadd("roles", values);
    }

    @Test
    public void addDepartment() {
        Map<String, String> department = new HashMap<>();
        department.put("userdp", "1");
        department.put("beadhousedp", "2");
        department.put("leisuregroupdp", "3");
        department.put("articledp", "4");
        department.put("systemdp", "5");
        redis.hmset("admindp", department);
    }

    @Test
    public void addDepartmentLeader() {
        Map<String, String> leaders = new HashMap<>();
        leaders.put("root", "root");
        leaders.put("useradmin", "useradmin");
        leaders.put("beadhouseadmin", "beadhouseadmin");
        leaders.put("artileadmin", "articleadmin");
        leaders.put("leisuregroupadmin", "leisuregroupadmin");
        leaders.put("sysadmin", "sysadmin");
        redis.hmset("leaders", leaders);
    }

    @Test
    public void redisPipelineTest() {
        Pipeline pipeline = redis.pipelined();
        pipeline.multi();
        Response<List<String>> responce = pipeline.lrange("notification:useradmin", 0, -1);
        pipeline.sync();
        for (String s : responce.get()) {
            System.out.println(s);
        }
        //pipeline.exec();
    }

    @Test
    public void cacheTest() {
        CacheStruct cacheStruct = new CacheStruct();
        cacheStruct.setKey("cache:my");
        cacheStruct.setValue("sb");
        cacheStruct.setLastVisitedTimeStamp(System.currentTimeMillis() / 1000);
        cacheStruct.setScore(0);
        CacheManager manager = new CacheManager();
        manager.addCache(cacheStruct);
    }

    @Test
    public void visitCacheTest() {
        List<String> strings = Arrays.asList("I", "am", "sb");
        Gson gson = new Gson();
        String s = gson.toJson(strings);
        System.out.println(s);
        List<String> past = gson.fromJson(s, List.class);
        for (String ss : past) {
            System.out.println(ss);
        }
    }
}
