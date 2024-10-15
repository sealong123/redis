package com.muggle;

import com.muggle.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemo2ApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void testString() {
        System.out.println("****");
        //写入一条String数据.
        redisTemplate.opsForValue().set("name","李四");
        //获取string数据.
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
    @Test
    public void testSaveUser(){
        redisTemplate.opsForValue().set("user:100",new User("张三",18));
        User user = (User)redisTemplate.opsForValue().get("user:100");
        System.out.println("user = " + user);
    }

}
