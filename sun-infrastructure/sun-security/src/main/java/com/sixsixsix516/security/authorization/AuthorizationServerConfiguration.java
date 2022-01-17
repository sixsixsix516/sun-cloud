package com.sixsixsix516.security.authorization;

import com.sixsixsix516.security.authorization.service.TokenService;
import com.sixsixsix516.security.client.Client;
import com.sixsixsix516.security.client.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.List;

/**
 * 授权服务器 配置
 *
 * @author SUN
 * @date 2022/1/15
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ClientDetailsService clientDetailsService;


    /**
     * 定义客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置授权服务Endpoint
     * <p>
     * SpringSecurityOAuth2会根据配置的认证服务，用户详情服务，令牌服务自动生成以下端点
     * /oauth/authorize 授权端点
     * /oauth/token 令牌端点
     * /oauth/confirm_access 用户确认授权提交端点
     * /oauth/error 授权服务错误信息端点
     * /oauth/check_token 资源服务访问的令牌解析端点
     * /oauth/token_key 提供公有密钥端点，如果JWT采用的是非对称加密加密算法，则资源服务其在鉴权时就需要这个公钥来解码
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置认证服务器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenService)
                // 配置TokenEndpoint端点请求访问类型, 默认 HttpMethod.POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 配置OAuth2发布出来的Endpoint的安全约束
     * <p>
     * 这些端点饿默认访问规则原本是
     * 1.端点开启了HTTP Basic Authentication，通过allowFormAuthenticationForClients()关闭, 即允许通过表单来验证
     * 2.端点的访问均为denyAll(), 可以在这里通过SpringEL表达式改变为permitAll()
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }
}
