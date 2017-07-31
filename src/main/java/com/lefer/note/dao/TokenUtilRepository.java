package com.lefer.note.dao;

import com.lefer.note.model.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fang
 * @creatdate 17-7-16
 */
@Repository
public interface TokenUtilRepository extends MongoRepository<TokenUtil,String>{
    public TokenUtil findByToken(String token);
}
