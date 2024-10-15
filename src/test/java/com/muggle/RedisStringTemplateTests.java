package com.muggle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muggle.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisStringTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString(){
        System.out.println("****");
        //写入一条String数据.
        stringRedisTemplate.opsForValue().set("name","李四");
        //获取string数据.
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testSaveUser() throws JsonProcessingException {
        //创建对象
        User user = new User("王五", 20);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        stringRedisTemplate.opsForValue().set("user:200",json);
        //获取数据
        String s = stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user1 = mapper.readValue(s, User.class);

        System.out.println("user1 = " + user1);
    }

}
