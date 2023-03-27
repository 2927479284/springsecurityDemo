package com.ddbh;


import com.ddbh.domain.SysUser;
import com.ddbh.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test1(){
        List<SysUser> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void test02(){
        System.out.println("test 02方法执行成功");
        String encode = passwordEncoder.encode("123456");
        //$2a$10$5K9tp9c0ogbOzhRBBW0/FOMcPgxMSAzCGtMtqqfF9UCgOFpjSzb9a
        System.out.println(encode);
        // 1.原文  2.密文
        boolean b = passwordEncoder.matches("123456789", "$2a$10$5K9tp9c0ogbOzhRBBW0/FOMcPgxMSAzCGtMtqqfF9UCgOFpjSzb9a");
        System.out.println(b);
    }
}
