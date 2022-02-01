package com.sixsixsix516.client.constant;

/**
 * 定义了令牌时间相关的常量
 *
 * @author SUN
 * @date 2022/1/17
 */
public interface TokenTime {

    /**
     * access_token 有效期 单位秒
     * 50年
     */
    int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 365 * 50;

    /**
     * refresh_token 有效期 单位秒
     * 15天
     * 当客户端选择 “记住当前登录用户”的最长时效, 即失效前都不用再请求用户赋权了
     */
    int REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 15;
}
