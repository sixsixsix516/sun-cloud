package com.sixsixsix516.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author SUN
 */
@EnableFeignClients("com.sixsixsix516")
@MapperScan("com.sixsixsix516.manager.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.sixsixsix516")
public class SunManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunManagerApplication.class, args);
    }

}
