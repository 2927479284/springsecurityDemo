package com.ddbh.service.impl;

import com.ddbh.domain.dto.UserLoginDto;
import com.ddbh.service.LoginUserService;
import com.ddbh.utils.AjaxResult;
import com.ddbh.utils.JwtUtil;
import com.ddbh.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录业务层实现
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisClient redisClient;


    @Override
    public AjaxResult login(UserLoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (Objects.isNull(authenticate)){
            return AjaxResult.error("登录失败/账号或者密码错误");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String s = userDetails.getUser().getUserId().toString();
        //生成jwt
        String jwt = JwtUtil.createJWT(s);
        //存进redis
        redisClient.set("com:ddbh:login:"+userDetails.getUser().getUserId(),userDetails.getUser(),10L, TimeUnit.HOURS);
        return AjaxResult.success().put("token",jwt);
    }

}
