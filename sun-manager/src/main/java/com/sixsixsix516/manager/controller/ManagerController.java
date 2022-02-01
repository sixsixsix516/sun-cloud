package com.sixsixsix516.manager.controller;

import com.sixsixsix516.common.vo.Result;
import com.sixsixsix516.user.api.api.UserApi;
import com.sixsixsix516.user.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SUN
 * @date 2022/1/15
 */
@RestController
public class ManagerController {

    @GetMapping("/get")
    public Result<User> get() {
        return userApi.findById(1L);
    }

    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("Hello I am manager");
    }

    @Autowired
    private UserApi userApi;
}
