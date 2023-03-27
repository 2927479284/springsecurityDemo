package com.ddbh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddbh.domain.SysUser;
import com.ddbh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.通过传入的用户名进行查询
        SysUser user = userMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName, s));
        if (user == null){
            throw  new RuntimeException("该账号不存在");
        }
        return new UserDetailsImpl(user);
    }
}
