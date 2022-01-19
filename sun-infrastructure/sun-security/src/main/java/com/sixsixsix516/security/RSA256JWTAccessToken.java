package com.sixsixsix516.security;

import com.sixsixsix516.common.security.jwt.JWTAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * 使用RSA SHA256私钥加密JWT令牌
 *
 * @author SUN
 * @date 2022/1/19
 */
@Component
public class RSA256JWTAccessToken extends JWTAccessToken {

    private final JsonParser objectMapper = JsonParserFactory.create();

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

    /**
     * 增强令牌Header
     * 使用JWKS验证令牌时， Header中需要有kid(Key ID), 设置Header的方法在Spring的默认实现中没有开放出来,这里添加个默认实现
     */
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Field singerFiled = ReflectionUtils.findField(this.getClass(), "singer");
        singerFiled.setAccessible(true);

        Signer signer = (Signer) ReflectionUtils.getField(singerFiled, this);

        String content = objectMapper.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));

        Map<String, String> headers = Collections.singletonMap("kid", "sun-jwt-kid");
        return JwtHelper.encode(content, signer, headers).getEncoded();
    }
}
