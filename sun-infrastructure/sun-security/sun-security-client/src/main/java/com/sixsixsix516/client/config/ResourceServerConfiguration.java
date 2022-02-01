package com.sixsixsix516.client.config;

import com.sixsixsix516.client.jwt.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 开启方法上的PreAuthorize注解: @EnableGlobalMethodSecurity
 * 增加条件 @ConditionalOnProperty(prefix = "security.oauth2.client", name = "client-id") 代表当前服务只有是客户端的时候在作为Bean
 * 因为当前模块会被sun-security-server和其他模块同时调用， 而 sun-security-server 不需要此配置，即他不是资源服务
 *
 * @author SUN
 * @date 2022/1/19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "security.oauth2.client", name = "client-id")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    /**
     * 配置HTTP访问相关的安全选项
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 基于JWT来绑定用户状态，所以服务端可以是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 关闭CSRF(Cross Site Request Forgery) 跨站请求伪造的防御
        // 因为需要状态存储CSRF Token 才能开启该功能
        http.csrf().disable();
        // 关闭HTTP Header的X-Frame-Options选项, 允许页面在frame标签中打开
        http.headers().frameOptions().disable();
        // 关闭HTTP Header中的Cache-Control:no-cache 允许缓存响应结果
        http.headers().cacheControl().disable();
        // 设置服务的默认安全规则
        // 在HTTP过滤器层面, 在所有的服务都允许未认证的访问
        // 在方法安全层面,每个方法上设置所需要的认证, 授权规则
        // 这里采用方式二来控制权限
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenService);
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }
}
