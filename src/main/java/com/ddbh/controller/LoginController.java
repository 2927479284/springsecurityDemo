package com.ddbh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(){
        System.out.println("login方法执行成功");
    }



    @GetMapping("/test")
    public void test(){
        System.out.println("test url访问成功");
    }
}
