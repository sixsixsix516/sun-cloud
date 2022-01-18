package com.sixsixsix516.user.controller;

import com.sixsixsix516.common.vo.Result;
import com.sixsixsix516.user.api.api.UserApi;
import com.sixsixsix516.user.api.model.User;
import com.sixsixsix516.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println();
        return Result.success(user);
    }


    @Autowired
    private UserMapper userMapper;

}
