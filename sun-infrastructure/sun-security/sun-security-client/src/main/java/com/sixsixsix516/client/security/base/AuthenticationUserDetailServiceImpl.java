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
        // username为 client:username 格式
        // TODO 优化项: clientId可以通过属性注入进来
        String[] usernameArray = username.split(":");

        String clientType = usernameArray[0];
        username = usernameArray[1];

        SecurityClientType[] securityClientTypes = SecurityClientType.values();
        for (SecurityClientType type : securityClientTypes) {
            if (type.getClient().equals(clientType)) {
                UserDetailsService userDetailsService = SpringUtil.getBean(type.getClientClass());
                // 用户名增加前缀,实现多账号体系
                BaseUserDetails userDetails = (BaseUserDetails) userDetailsService.loadUserByUsername(username);
                userDetails.setUsername(String.join(":", clientType, userDetails.getUsername()));
                return userDetails;
            }
        }
        throw new UsernameNotFoundException("Cannot find username: " + username);
    }
}