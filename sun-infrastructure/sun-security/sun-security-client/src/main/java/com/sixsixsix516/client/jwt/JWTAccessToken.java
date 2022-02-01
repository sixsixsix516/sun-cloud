package com.sixsixsix516.client.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT访问令牌
 *
 * @author SUN
 * @date 2022/1/16
 */
public class JWTAccessToken extends JwtAccessTokenConverter {

    /**
     * 在令牌负载中加入额外信息
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Authentication user = authentication.getUserAuthentication();
        // 密码模式才会有用户信息, 在微服务之间的客户端认证模式下不需要增强令牌
        if (user != null) {
            String[] authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);

            Map<String, Object> payload = new HashMap<>(2);
            payload.put("username", user.getName());
            payload.put("authorities", authorities);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(payload);
        }
        return super.enhance(accessToken, authentication);
    }
}
