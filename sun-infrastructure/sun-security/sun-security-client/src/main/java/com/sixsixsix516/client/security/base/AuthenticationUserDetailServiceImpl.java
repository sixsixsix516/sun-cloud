package com.sixsixsix516.client.security.base;

import com.sixsixsix516.client.security.SecurityClientType;
import com.sixsixsix516.client.utils.SpringUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 *
 * @author SUN
 * @date 2022/1/15
 */
@Primary
@Service
public class AuthenticationUserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username改为 client:username 格式
        String[] usernameArray = username.split(":");

        String clientType = usernameArray[0];
        username = usernameArray[1];

        SecurityClientType[] securityClientTypes = SecurityClientType.values();
        for (SecurityClientType type : securityClientTypes) {
            if (type.getClient().equals(clientType)) {
                UserDetailsService userDetailsService = SpringUtil.getBean(type.getClientClass());
                return userDetailsService.loadUserByUsername(username);
            }
        }
        throw new UsernameNotFoundException("Cannot find username: " + username);
    }

}
