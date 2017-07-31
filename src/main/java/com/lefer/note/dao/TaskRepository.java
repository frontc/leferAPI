package com.lefer.note.dao;

import com.lefer.note.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fang
 * @creatdate 17-7-16
 */

@Repository
public interface TaskRepository extends MongoRepository<Task,String>{
    public List<Task> findByUserName(String userName);
    public List<Task> findByTaskText(String taskText);
    public Task findByTaskId(Long taskId);
    @Query("{'userName':?0},{'isCompleted':?1}")
    public List<Task> findByUserNameAndCompleted(String userName,Boolean isCompleted);
}
