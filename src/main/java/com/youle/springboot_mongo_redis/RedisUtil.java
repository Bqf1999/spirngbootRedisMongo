package com.youle.springboot_mongo_redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    public RedisUtil(RedisTemplate<String,Object>redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    /**
     * 指定缓存失效时间
     */
    public boolean expire(String key,long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    /**
     * 根据key获取过期时间
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
    /**
     * 判断key是否存在
     */
    public boolean hashKey(String key){
        try{
            return  redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 删除缓存
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }
    /**
     * 普通缓存获取
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 普通缓存放入并设置时间
     */
    public boolean set(String key,Object value,long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key, value, time,TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
