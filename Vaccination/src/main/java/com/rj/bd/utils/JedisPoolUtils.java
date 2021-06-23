package com.rj.bd.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @desc Redis数据库连接池   用于获取 redis 数据库连接对象
 * @author 秋枫
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建Jedis配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        // 初始化
        jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
    }

    // 获取连接
    public static Jedis getJedisSession()
    {
        return jedisPool.getResource();
    }


    /**
     * @desc 存入Map
     * @param redisConfig
     * @param key
     * @param map
     * @return
     */
    public static boolean setMap(Jedis redisConfig,String key, Map<String, Object> map)  {
        try {
            HashMap<String, String> stringHashMap = new HashMap<>();
            //Object键值转为String
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                stringHashMap.put(entry.getKey(),entry.getValue().toString());
            }
            redisConfig.hmset(key,stringHashMap);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @desc  取出Map   如果有则返回 map 没有则返回 null
     * @param redisConfig
     * @param key
     * @return
     */
    public static Map<String, String> getMap(Jedis redisConfig,String key){
        return redisConfig.hgetAll(key);
    }

    /**
     *
     * @desc 删除Map 字典的某个键值
     */
    public static boolean deleteMapKey(Jedis redisConfig,String mapKey,String key){
        Long hdel = redisConfig.hdel(mapKey, key);
        return hdel != 0;
    }



}
