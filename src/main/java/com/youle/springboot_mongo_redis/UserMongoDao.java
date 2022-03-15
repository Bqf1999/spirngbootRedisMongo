package com.youle.springboot_mongo_redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserMongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 创建对象
     */
    public void saveMongo(User user){
        mongoTemplate.save(user);
    }
    /**
     * 根据id查询对象
     */
    public User findById(Long id){
        return mongoTemplate.findById(id,User.class);
    }
}
