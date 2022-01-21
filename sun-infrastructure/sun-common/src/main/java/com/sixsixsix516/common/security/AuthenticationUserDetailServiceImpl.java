package com.sixsixsix516.common.security;

import com.sixsixsix516.common.security.account.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 认证服务
 *
 * @author SUN
 * @date 2022/1/15
 */
@Service
public class AuthenticationUserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 从数据库查询
        ServiceAccount serviceAccount = new ServiceAccount();
        Collection<GrantedAuthority> authorities = serviceAccount.getAuthorities();
        // 增加一个权限
        authorities.add(new SimpleGrantedAuthority("admin"));
        serviceAccount.setUsername(username);
        serviceAccount.setPassword(passwordEncoder.encode(username));
        return serviceAccount;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

}
