package com.sixsixsix516.user.api.config;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SUN
 * @date 2022/1/18
 */
@Slf4j
//@Configuration
public class HttpBasicConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        log.info("BasicAuthRequestInterceptor初始化!!!!!");
        return new BasicAuthRequestInterceptor("sun-user", "sun-user");
    }
}
