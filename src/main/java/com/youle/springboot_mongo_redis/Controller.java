package com.youle.springboot_mongo_redis;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class Controller {

    private static int ExpireTime=60;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserMongoDao mongoDao;

    /**
     * 向redis 插入数据，后向mongodb插入数据。
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/createUser")
    public Response saveUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name){
        User user=new User();
        user.setUserId(id);
        user.setName(name);
        boolean RedisRes=redisUtil.set(String.valueOf(id),name,ExpireTime);
        mongoDao.saveMongo(user);
        Response response=new Response(RedisRes,user);
        return response;
    }

    /**
     * 首先向redis中查询，若redis中不存在，向mongodb查询后将数据插入redis
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUser")
    public  Response getUser(@RequestParam("id") String id){

        Object obj=redisUtil.get(id);
        boolean isOk=true;
        User user= (User) obj;
        if(obj==null)  //redis缓存中没有，
        {
            user=mongoDao.findById(Long.valueOf(id));

            isOk=redisUtil.set(user.getUserId().toString(),user.getName(),ExpireTime);

            obj=user;
        }
        Response response=new Response(isOk,user);
        return response;
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}
