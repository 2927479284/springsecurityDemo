package com.ddbh.controller;

import com.ddbh.domain.SysUser;
import com.ddbh.domain.dto.UserLoginDto;
import com.ddbh.mapper.UserMapper;
import com.ddbh.service.LoginUserService;
import com.ddbh.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户登录控制层
 */
@RestController
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginUserService loginUserService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDto loginDto){
        return loginUserService.login(loginDto);
    }



    @GetMapping("/test")
    public void test(){
        List<SysUser> users = userMapper.selectList(null);
        System.out.println(users);
        System.out.println("test url访问成功");
    }
}
