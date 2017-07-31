package com.lefer.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author fang
 * @creatdate 17-7-13
 */
@Document(collection = "noteUser")
public class User implements Serializable{
    private String openId;
    private String userName;
    private String password;

    public User(String openId, String userName, String password) {
        this.openId = openId;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId=" + openId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
