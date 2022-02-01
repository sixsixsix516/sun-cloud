package com.sixsixsix516.client.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * 使用RSA SHA256公钥解密的JWT令牌
 * @author SUN
 * @date 2022/1/19
 */
@ConditionalOnProperty(prefix = "security.oauth2.client", name = "client-id")
@Component
public class RSA256PublicJWTAccessToken extends JWTAccessToken {

    @Autowired
    public RSA256PublicJWTAccessToken(UserDetailsService userDetailsService) throws IOException {
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(userDetailsService);
        ((DefaultAccessTokenConverter) getAccessTokenConverter()).setUserTokenConverter(defaultUserAuthenticationConverter);

        Resource resource = new ClassPathResource("public.cert");
        // 拿到公钥内容
        String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        setVerifierKey(publicKey);
    }

}
