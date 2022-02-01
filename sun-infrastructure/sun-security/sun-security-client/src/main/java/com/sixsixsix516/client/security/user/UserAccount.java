package com.sixsixsix516.client.security.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author SUN
 * @date 2022/1/15
 */
@Data
public class UserAccount implements UserDetails {

    private String username;

    private String password;

    /**
     * 该用户拥有的授权
     */
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号未过期 ?
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号未锁定 ?
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码未过期 ?
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否启用 ?
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
