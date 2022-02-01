package com.sixsixsix516.client.security.user;

import com.sixsixsix516.client.security.SecurityClientType;
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
 * @author SUN
 * @date 2022/1/31
 */
@Service
public class UserClientUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 查询用户端用户
        UserAccount serviceAccount = new UserAccount();
        Collection<GrantedAuthority> authorities = serviceAccount.getAuthorities();
        // 增加一个权限
        authorities.add(new SimpleGrantedAuthority("admin"));
        serviceAccount.setUsername(String.join(":", SecurityClientType.CLIENT_USER.getClient(), username));
        serviceAccount.setPassword(passwordEncoder.encode(username));
        return serviceAccount;
    }
}
