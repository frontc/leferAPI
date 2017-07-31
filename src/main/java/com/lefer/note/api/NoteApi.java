package com.lefer.note.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lefer.common.Result;
import com.lefer.common.ResultGenerator;
import com.lefer.note.dao.TaskRepository;
import com.lefer.note.dao.TokenUtilRepository;
import com.lefer.note.dao.UserRepository;
import com.lefer.note.model.Task;
import com.lefer.note.model.TokenUtil;
import com.lefer.note.model.User;
import com.lefer.note.service.LoginService;
import com.lefer.note.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Clock;

/**
 * @author fang
 * @creatdate 17-7-14
 */
@RestController
@RequestMapping(path = "/note")
public class NoteApi {
    @Value("${wx.appid}")
    private String wxAppId;
    @Value("${wx.secret}")
    private String wxSecret;
    @Value("${wx.url}")
    private String wxUrl;
    @Value("${wx.grantType}")
    private String grantType;
    @Value("${note.url}")
    private String noteUrl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TokenUtilRepository tokenUtilRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(path = "/onLogin", method = RequestMethod.GET)
    public Result onLogin(@RequestParam String code) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(wxUrl)
                .queryParam("appid", wxAppId)
                .queryParam("secret", wxSecret)
                .queryParam("js_code", code)
                .queryParam("grant_type", grantType);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String openid = jsonNode.path("openid").asText();
            LoggerFactory.getLogger(NoteApi.class).info(openid);
            if ("".equals(openid)) {
                LoggerFactory.getLogger(NoteApi.class).info(openid);
                return ResultGenerator.genFailResult(jsonNode.path("errcode").asText() + " " + jsonNode.path("errmsg").asText());
            }
            User user = userRepository.findByOpenId(openid);
            if (user == null) {
                return ResultGenerator.genUnauthorizedResult(openid);
            }
            String token = loginService.doLogin(user);
            if (token == null) {
                return ResultGenerator.genUnauthorizedResult(openid);
            }
            TokenUtil t = new TokenUtil();
            t.setOpenId(openid);
            t.setToken(token);
            t.setUserName(user.getUserName());
            tokenUtilRepository.save(t);
            return ResultGenerator.genSuccessResult(loginService.doLogin(user));
        } catch (Exception e) {
            LoggerFactory.getLogger(NoteApi.class).info(e.toString());
            return ResultGenerator.genFailResult("无法解析微信返回的jsonString！");
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public Result login(@RequestParam String key, @RequestParam String userName, @RequestParam String password) {
        User user = new User();
        user.setOpenId(key);
        user.setUserName(userName);
        user.setPassword(password);
        String token = loginService.doLogin(user);
        if (token == null) {
            return ResultGenerator.genFailResult("用户名或密码错误！");
        }
        userRepository.removeByUserName(userName);
        userRepository.save(user);
        TokenUtil t= new TokenUtil();
        t.setUserName(userName);
        t.setToken(token);
        t.setOpenId(key);
        tokenUtilRepository.save(t);
        return ResultGenerator.genSuccessResult(token);
    }

    @RequestMapping(path = "/getTasks",method = RequestMethod.GET)
    public Result getTasks(@RequestParam String token,@RequestParam Boolean completed){
        return ResultGenerator.genSuccessResult(taskService.getTasksByToken(token,completed));
    }

    @RequestMapping(path="/addTask",method = RequestMethod.POST)
    public Result addTask(@RequestParam String token,@RequestParam String taskText){
        String userName=tokenUtilRepository.findByToken(token).getUserName();
        Task task = new Task();
        task.setUserName(userName);
        task.setTaskText(taskText);
        task.setCompleted(false);
        task.setTaskId(Clock.systemUTC().millis());
        taskRepository.save(task);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(path="/completeTask",method = RequestMethod.POST)
    public Result completeTask(@RequestParam String token,@RequestParam Long[] taskIdList){
        for(int i=0;i<taskIdList.length;i++){
            Task task = taskRepository.findByTaskId(taskIdList[i]);
            taskRepository.delete(task);
            task.setCompleted(true);
            taskRepository.save(task);
        }
        return ResultGenerator.genSuccessResult();
    }
}
