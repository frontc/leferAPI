package com.lefer.note.dao;

import com.lefer.note.model.User;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

/**
 * @author fang
 * @creatdate 17-7-13
 */
@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StringEncryptor stringEncryptor;

    public void save(User user) {
        user.setPassword(stringEncryptor.encrypt(user.getPassword()));
        mongoTemplate.save(user);
    }

    public User findByUserName(String userName) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("userName").is(userName)), User.class);
        if(user == null){
            return null;
        }else{
            user.setPassword(stringEncryptor.decrypt(user.getPassword()));
            return user;
        }
    }

    public User findByOpenId(String openId) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("openId").is(openId)), User.class);
        if(user==null){
            return null;
        }else{
            user.setPassword(stringEncryptor.decrypt(user.getPassword()));
            return user;
        }
    }

    public void removeByUserName(String userName){
        mongoTemplate.findAndRemove(Query.query(Criteria.where("userName").is(userName)),User.class);
    }
}
