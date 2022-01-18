package com.sixsixsix516.user;

import feign.auth.BasicAuthRequestInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author SUN
 */
@MapperScan("com.sixsixsix516.user.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.sixsixsix516")
public class SunUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunUserApplication.class, args);
    }


}
