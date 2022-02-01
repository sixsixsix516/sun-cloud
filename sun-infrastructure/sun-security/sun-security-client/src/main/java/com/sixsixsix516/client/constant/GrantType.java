package com.sixsixsix516.client.constant;

/**
 * OAuth2 授权类型
 * @author SUN
 * @date 2022/1/16
 */
public interface GrantType {

    /**
     * 密码模式
     */
    String PASSWORD = "password";

    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    String IMPLICIT = "implicit";

    /**
     * 授权码模式
     */
    String AUTHORIZATIONCODE = "authorizationcode";

    /**
     * 刷新令牌
     */
    String REFRESH_TOKEN = "refresh_token";

}
