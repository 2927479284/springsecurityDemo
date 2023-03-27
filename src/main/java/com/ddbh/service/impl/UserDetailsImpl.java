package com.ddbh.service.impl;

import com.ddbh.domain.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private SysUser user;

    public UserDetailsImpl(SysUser user) {
        this.user = user;
    }

    /**
     * 权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 用户密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 用户账号
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 是否未被锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 是否已启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
