package com.sixsixsix516.security.authorization.service;

import com.sixsixsix516.security.jwt.JWTAccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

/**
 * 自定义令牌配置
 *
 * @author SUN
 * @date 2022/1/15
 */
@Service
public class TokenService extends DefaultTokenServices {

    public TokenService(JWTAccessToken token) {
        setTokenStore(new JwtTokenStore(token));
    }


}
