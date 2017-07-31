package com.lefer.common;

/**
 * Created by fang on 17-7-3.
 */
public class Result {
    private int status;
    private String message;
    private Object data;

    public Result setStatus(ResultStatus resultStatus) {
        this.status = resultStatus.status;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Result setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
