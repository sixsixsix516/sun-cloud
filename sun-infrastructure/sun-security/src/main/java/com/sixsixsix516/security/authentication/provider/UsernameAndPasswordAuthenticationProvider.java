package com.sixsixsix516.security.authentication.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 基于用户名 密码的身份认证器
 *
 * @author SUN
 * @date 2022/1/15
 */
@Component
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 进行认证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (!matches) {
            throw new BadCredentialsException("密码错误");
        }

        // 认证通过 返回令牌
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    /**
     * 该认证器能处理哪些认证?
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
