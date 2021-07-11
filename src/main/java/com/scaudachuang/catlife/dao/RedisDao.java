package com.scaudachuang.catlife.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hiluyx
 * @since 2021/6/26 17:26
 **/
@Repository
public class RedisDao {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void expireOnHours(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.HOURS);
    }

    public long getExpire(String key) {
        Long time = redisTemplate.getExpire(key);
        if (time == null) return -1;
        return time;
    }

    public boolean hasKey(String key){
        Boolean b = redisTemplate.hasKey(key);
        if (b == null) return false;
        return b;
    }
}
