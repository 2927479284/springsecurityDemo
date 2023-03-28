package com.ddbh;

import com.ddbh.domain.SysUser;
import com.ddbh.utils.JwtUtil;
import com.ddbh.utils.RedisClient;
import com.ddbh.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义filter 过滤当前是否有token 没有则放行
 */
@Component
public class zdyFilter extends OncePerRequestFilter {

    @Autowired
    private RedisClient redisClient;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        // 查询当前token是否合法
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token 非法");
        }
        // 查询当前用户是否失效
        SysUser sysUser = redisClient.get("com:ddbh:login:" + userId, SysUser.class);
        if (StringUtils.isEmpty(sysUser.getUserName())){
            throw new RuntimeException("用户登录状态失效");
        }
        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(sysUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(token1);
        //继续放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
