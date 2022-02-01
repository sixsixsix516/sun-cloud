package com.sixsixsix516.user.controller;

import com.sixsixsix516.common.vo.Result;
import com.sixsixsix516.user.api.api.UserApi;
import com.sixsixsix516.user.api.model.User;
import com.sixsixsix516.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SUN
 * @date 2022/1/15
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

    @Override
    @GetMapping("/{userId}")
    public Result<User> findById(@PathVariable Long userId) {
        User user = userMapper.selectById(userId);
        return Result.success(user);
    }

    // TODO
    @PostMapping("/login")
    public Result login() {
        return Result.success(null);
    }

    /*    */
    /**
     * 获取正在登录的用户
     *//*
    @GetMapping("/my")
    public Result<ServiceAccount> my() {
        ServiceAccount loginUser = UserUtil.getLoginUser();
        return Result.success(loginUser);
    }*/


    @Autowired
    private UserMapper userMapper;

}
