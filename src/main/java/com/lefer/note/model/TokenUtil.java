package com.lefer.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author fang
 * @creatdate 17-7-16
 */
@Document(collection = "userToken")
public class TokenUtil implements Serializable{
    private String openId;
    private String token;
    private String userName;

    public TokenUtil(String openId, String token, String userName) {
        this.openId = openId;
        this.token = token;
        this.userName = userName;
    }

    public TokenUtil() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
