package com.ddbh;


import com.ddbh.domain.User;
import com.ddbh.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test1(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void test02(){
        System.out.println("test 02方法执行成功");
        String encode = bCryptPasswordEncoder.encode("123456789");
        //$2a$10$DRX2xp4JzS7zmgZYQcUG6uV1E1YyAtTXsiEp8bQ1WTNxGM1NhqGza
        System.out.println(encode);
        // 1.原文  2.密文
        boolean b = bCryptPasswordEncoder.matches("123456789", "$10$DRX2xp4JzS7zmgZYQcUG6uV1E1YyAtTXsiEp8bQ1WTNxGM1NhqGza");
        System.out.println(b);
    }
}
