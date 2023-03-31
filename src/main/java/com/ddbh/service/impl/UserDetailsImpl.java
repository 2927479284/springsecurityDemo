package com.ddbh.service.impl;

import com.ddbh.domain.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private SysUser user;

    public UserDetailsImpl(SysUser user) {
        this.user = user;
    }

    public UserDetailsImpl(SysUser user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * 查询出来的权限集合
     */
    List<String> permissions;

    List<SimpleGrantedAuthority> authorities;
    /**
     * 权限
     * @return Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    /**
     * 用户密码
     * @return String
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 用户账号
     * @return String
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 是否未过期
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 是否未被锁定
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 是否已启用
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
