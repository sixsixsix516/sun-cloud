package com.sixsixsix516.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 认证、授权
 * @author SUN
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.sixsixsix516")
public class SunSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunSecurityApplication.class, args);
    }


}
