package com.lefer;

import com.lefer.note.dao.UserRepository;
import com.lefer.note.model.User;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    StringEncryptor stringEncryptor;
	@Test
	public void contextLoads() {
        Clock clock= Clock.systemUTC();//获取格林尼治时间
        System.out.println(clock.instant());//获取Instant类型数据，后面会讲到
        System.out.println(clock.millis());//获取标准毫秒数
	}
    @Test
    public void genPassword(){
        System.out.println(stringEncryptor.encrypt("odsods"));
        System.out.println(stringEncryptor.decrypt("K10/vn0ohjZtB0FeARJuig=="));
    }

}
