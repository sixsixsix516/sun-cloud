package com.sixsixsix516.client.security;

import com.sixsixsix516.client.security.user.UserClientUserDetailService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 这个类定义了当前系统所有账号体系，例如后台管理登录用户，APP前端登录用户等
 *
 * @author SUN
 * @date 2022/1/31
 */
@Getter
@AllArgsConstructor
public enum SecurityClientType {

    /**
     * 用户模块客户端
     */
    CLIENT_USER("CLIENT_USER", UserClientUserDetailService.class);

    /**
     * 客户端字符串标识，此字段加入到用户名前面，来实现多账号安全功能（自定义不重复）
     */
    String client;
    /**
     * 当前客户端对应的UserDetailsService类，每个账号都有各自的UserDetailsService实现，来完成查询用户
     */
    Class<? extends UserDetailsService> clientClass;

}
