package com.scaudachuang.catlife.dao;

import com.scaudachuang.catlife.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hiluyx
 * @since 2021/6/26 17:26
 **/
@Repository
public class RedisDao {
    /*
    * login key : online_ownerid
    *
    * session value : {
    *   task_num :
    *   task_history_num :
    *
    * }
    *
    * */
    public static final String ONLINE_PREFIX = "online_";

    @Resource
    private RedisTemplate<String, Object> sessionRedisTemplate;

    public void delSession(String key) {
        sessionRedisTemplate.delete(key);
    }

    public void setSession(String ownerId, UserSession value, long l) {
        sessionRedisTemplate.opsForValue().set(ONLINE_PREFIX + ownerId, value, l, TimeUnit.HOURS);
    }

    public void expireOn1Hour(String key) {
        expireOnHours(key, 1);
    }

    public void expireOnHours(String key, long time) {
        sessionRedisTemplate.expire(key, time, TimeUnit.HOURS);
    }

    public long getExpire(String key) {
        Long time = sessionRedisTemplate.getExpire(key);
        if (time == null) return -1;
        return time;
    }

    public boolean hasKey(String key){
        Boolean b = sessionRedisTemplate.hasKey(key);
        if (b == null) return false;
        return b;
    }
}
