package com.lefer.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author fang
 * @creatdate 17-7-16
 */
@Document(collection = "tasks")
public class Task implements Serializable{
    @Id
    private Long taskId;
    private String taskText;
    private String userName;
    private Boolean isCompleted;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Task() {
    }

    public Task(Long taskId, String taskText, String userName, Boolean isCompleted) {
        this.taskId = taskId;
        this.taskText = taskText;
        this.userName = userName;
        this.isCompleted = isCompleted;
    }
}
