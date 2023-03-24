package com.ddbh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("/js/**","/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();*/
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll()
                //指登录成功后，是否始终跳转到登录成功url。它默认为false
                .defaultSuccessUrl("/index.html",true)
                //post登录接口，登录验证由系统实现
                .loginProcessingUrl("/login")
                //用户密码错误跳转接口
                .failureUrl("/error.html")
                //要认证的用户参数名，默认username
                .usernameParameter("userName")
                //要认证的密码参数名，默认password
                .passwordParameter("password")
                //配置注销
                .and()
                .logout()
                //注销接口
                .logoutUrl("/logout")
                //注销成功后跳转的页面
                .logoutSuccessUrl("/login.html")
                .permitAll()
                //删除自定义的cookie
                .deleteCookies("mycookie")
                .and()
                //关闭防护功能
                .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
