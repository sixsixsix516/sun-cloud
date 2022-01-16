package com.sixsixsix516.security.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SUN
 * @date 2022/1/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 授权类型
     *
     * 见 {@link com.sixsixsix516.security.constant.GrantType}
     */
    private String[] grantTypes;

    /**
     * 授权范围
     *
     * 见 {@link com.sixsixsix516.security.constant.Scope}
     */
    private String[] scopes;
}
