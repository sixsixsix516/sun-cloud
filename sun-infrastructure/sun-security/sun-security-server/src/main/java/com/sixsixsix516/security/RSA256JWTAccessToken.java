package com.sixsixsix516.security;

import com.sixsixsix516.client.jwt.JWTAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

/**
 * 使用RSA SHA256私钥加密JWT令牌
 *
 * @author SUN
 * @date 2022/1/19
 */
@Component
public class RSA256JWTAccessToken extends JWTAccessToken {

    @Autowired
    public RSA256JWTAccessToken(UserDetailsService userDetailsService) {
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(userDetailsService);
        ((DefaultAccessTokenConverter) getAccessTokenConverter()).setUserTokenConverter(defaultUserAuthenticationConverter);

        // 私钥密码
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("rsa.jks"), "SUN123456".toCharArray());
        // 别名
        setKeyPair(keyStoreKeyFactory.getKeyPair("SUN"));
    }
}
