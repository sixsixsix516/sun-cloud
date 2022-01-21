package com.sixsixsix516.security.authentication;

import com.sixsixsix516.common.security.UsernameAndPasswordAuthenticationProvider;
import com.sixsixsix516.security.authorization.AuthorizationServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 认证服务器配置
 *
 * @author SUN
 * @date 2022/1/15
 */
@EnableWebSecurity
public class AuthenticationServerConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsernameAndPasswordAuthenticationProvider usernameAndPasswordAuthenticationProvider;

    /**
     * 需要把AuthenticationManager主动暴漏出来
     * 以便在授权服务器{@link AuthorizationServerConfiguration}中可以使用它来完成用户名、密码的认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                // 配置密码编码器
                .passwordEncoder(encoder);
        auth.authenticationProvider(usernameAndPasswordAuthenticationProvider);
    }

}
