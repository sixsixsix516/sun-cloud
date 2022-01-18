package com.sixsixsix516.security.client;

import com.sixsixsix516.security.constant.GrantType;
import com.sixsixsix516.security.constant.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
