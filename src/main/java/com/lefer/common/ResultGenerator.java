package com.lefer.common;

/**
 * Created by fang on 17-7-3.
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setStatus(ResultStatus.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setStatus(ResultStatus.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setStatus(ResultStatus.FAIL)
                .setMessage(message);
    }

    public static Result genUnauthorizedResult(Object data){
        return new Result()
                .setStatus(ResultStatus.UNAUTHORIZED)
                .setMessage("未绑定帐号或Note密码已失效，请重新登录！")
                .setData(data);
    }
}

