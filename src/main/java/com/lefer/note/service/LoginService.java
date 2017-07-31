package com.lefer.note.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lefer.note.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author fang
 * @creatdate 17-7-14
 */
@Service
public class LoginService {
    @Value("${note.url}")
    private String noteUrl;

    public String doLogin(User user){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(noteUrl+"/auth/login")
                .queryParam("email", user.getUserName())
                .queryParam("pwd", user.getPassword());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String token = jsonNode.path("Token").asText();
            if("".equals(token)){
                return null;
            }
            return token;
        }catch (Exception e){
            LoggerFactory.getLogger(LoginService.class).info(e.toString());
            return null;
        }
    }
}
