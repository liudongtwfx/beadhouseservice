package com.beadhouse.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<String> values = redis.lrange("notification:useradmin", 0, -1);
        for (String v : values) {
            System.out.println(v);
        }
    }
}
