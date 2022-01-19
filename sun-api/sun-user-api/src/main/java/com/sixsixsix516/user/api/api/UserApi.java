package com.sixsixsix516.user.api.api;

import com.sixsixsix516.common.vo.Result;
import com.sixsixsix516.user.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author SUN
 * @date 2022/1/14
 */
@FeignClient(value = "sun-user", path = "/user/user")
public interface UserApi {

    /**
     * 根据用户id查询用户详情
     *
     * @param userId 用户id
     * @return 用户详情
     */
    @GetMapping("/{userId}")
    Result<User> findById(@PathVariable Long userId);

}
