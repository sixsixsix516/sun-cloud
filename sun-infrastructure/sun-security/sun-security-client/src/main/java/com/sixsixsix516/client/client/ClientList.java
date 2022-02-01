package com.sixsixsix516.client.client;

import com.sixsixsix516.client.constant.GrantType;
import com.sixsixsix516.client.constant.Scope;

import java.util.Arrays;
import java.util.List;

/**
 * 客户端列表
 *
 * @author SUN
 * @date 2022/1/16
 */
public class ClientList {

    public static List<Client> clients = Arrays.asList(
            // 后台管理前端服务
            new Client("sun-web", "sun-web", new String[]{GrantType.PASSWORD, GrantType.REFRESH_TOKEN}, new String[]{Scope.BROWSER}),
            // 微服务 - 用户端
            new Client("sun-user", "sun-user", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE}),
            // 微服务 - 后台管理
            new Client("sun-manager", "sun-manager", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE})
    );

}
