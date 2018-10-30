package com.lefer.note.api;

import com.lefer.common.Result;
import com.lefer.common.ResultGenerator;
import com.lefer.common.Tools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 密码生成服务
 *
 * @author fangchao
 * @since 2018-10-29 15:28
 **/
@RestController
@RequestMapping(path = "/key")
public class PasswordApi {
    @RequestMapping(path = "/verify", method = RequestMethod.GET)
    public Result verify(@RequestParam String key){
        return Tools.verify(key)?ResultGenerator.genSuccessResult():ResultGenerator.genFailResult("key is error");
    }

    @RequestMapping(path = "/generate", method = RequestMethod.GET)
    public Result generate(){
        return ResultGenerator.genSuccessResult(Tools.generateKey());
    }
}
