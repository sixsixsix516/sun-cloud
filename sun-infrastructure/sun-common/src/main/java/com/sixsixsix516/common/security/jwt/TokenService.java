package com.sixsixsix516.common.security.jwt;


import com.sixsixsix516.common.security.RSA256PublicJWTAccessToken;
import com.sixsixsix516.common.security.client.OAuthClientDetailService;
import com.sixsixsix516.common.security.constant.TokenTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 自定义令牌配置
 *
 * @author SUN
 * @date 2022/1/15
 */
@Service
public class TokenService extends DefaultTokenServices {

    @Autowired
    public TokenService(RSA256PublicJWTAccessToken token, OAuthClientDetailService clientDetailsService, Optional<AuthenticationManager> authenticationManager) {
        // 令牌持久化容器
        setTokenStore(new JwtTokenStore(token));
        //令牌支持的客户端详情
        setClientDetailsService(clientDetailsService);
        // 认证管理器
//        setAuthenticationManager(authenticationManager);
        setAuthenticationManager(authenticationManager.orElseGet(() -> {
            OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
            manager.setClientDetailsService(clientDetailsService);
            manager.setTokenServices(this);
            return manager;
        }));
        // 令牌额外负载
        setTokenEnhancer(token);
        // accessToken有效期 单位秒
        setAccessTokenValiditySeconds(TokenTime.ACCESS_TOKEN_VALIDITY_SECONDS);
        // refresh_token的有效期，单位：秒
        setRefreshTokenValiditySeconds(TokenTime.REFRESH_TOKEN_VALIDITY_SECONDS);
        // 是否支持refresh_token
        setSupportRefreshToken(true);
        // 是否复用refresh_token，默认为true
        // 如果为false，则每次请求刷新都会删除旧的refresh_token，创建新的refresh_token
        setReuseRefreshToken(true);
    }
}
