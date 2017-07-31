package com.lefer.note.service;

import com.lefer.note.dao.TaskRepository;
import com.lefer.note.dao.TokenUtilRepository;
import com.lefer.note.model.Task;
import com.lefer.note.model.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fang
 * @creatdate 17-7-16
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TokenUtilRepository tokenUtilRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Task> getTasksByToken(String token){
        TokenUtil tokenUtil=tokenUtilRepository.findByToken(token);
        String userName = tokenUtil.getUserName();
        return taskRepository.findByUserName(userName);
    }

    public List<Task> getTasksByToken(String token,Boolean isCompleted){
        TokenUtil tokenUtil=tokenUtilRepository.findByToken(token);
        String userName = tokenUtil.getUserName();

        List<Task> tasks = mongoTemplate.find(Query.query(Criteria.where("userName").is(userName).andOperator(Criteria.where("isCompleted").is(isCompleted))),Task.class);
        //return taskRepository.findByUserNameAndCompleted(userName,isCompleted);
        return tasks;
    }
}
