package com.sixsixsix516.client.utils;

import com.sixsixsix516.client.security.user.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author SUN
 * @date 2022/1/21
 */
public class UserUtil {

    /**
     * 获取正在登录的用户
     */
    public static UserAccount getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserAccount) authentication.getPrincipal();
    }
}
