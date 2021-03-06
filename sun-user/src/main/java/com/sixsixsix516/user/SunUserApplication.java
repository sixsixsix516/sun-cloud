package com.sixsixsix516.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SUN
 */
@MapperScan("com.sixsixsix516.user.mapper")
@SpringBootApplication(scanBasePackages = "com.sixsixsix516")
public class SunUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunUserApplication.class, args);
    }

}
