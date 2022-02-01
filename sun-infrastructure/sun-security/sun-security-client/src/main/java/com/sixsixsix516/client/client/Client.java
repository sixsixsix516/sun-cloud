package com.sixsixsix516.client.client;

import com.sixsixsix516.client.constant.GrantType;
import com.sixsixsix516.client.constant.Scope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代表一个客户端
 *
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
     * 见 {@link GrantType}
     */
    private String[] grantTypes;

    /**
     * 授权范围
     *
     * 见 {@link Scope}
     */
    private String[] scopes;
}
