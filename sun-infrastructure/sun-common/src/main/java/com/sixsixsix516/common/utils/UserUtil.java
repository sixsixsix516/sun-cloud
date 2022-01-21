package com.sixsixsix516.common.utils;

import com.sixsixsix516.common.security.account.ServiceAccount;
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
    public static ServiceAccount getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ServiceAccount) authentication.getPrincipal();
    }
}
