package com.sixsixsix516.security.authorization;

import com.sixsixsix516.common.security.client.OAuthClientDetailService;
import com.sixsixsix516.common.security.jwt.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;


/**
 * 授权服务器 配置
 *
 * @author SUN
 * @date 2022/1/15
 */
@Slf4j
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
    private OAuthClientDetailService clientDetailsService;


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
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置认证服务器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenService)
                // 配置TokenEndpoint端点请求访问类型, 默认 HttpMethod.POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);


        DefaultWebResponseExceptionTranslator defaultWebResponseExceptionTranslator = new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                log.error("生成token异常", e);
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
            }
        };
        endpoints.exceptionTranslator(defaultWebResponseExceptionTranslator);
    }

    /**
     * 配置OAuth2发布出来的Endpoint的安全约束
     * <p>
     * 这些端点饿默认访问规则原本是
     * 1.端点开启了HTTP Basic Authentication，通过allowFormAuthenticationForClients()关闭, 即允许通过表单来验证
     * 2.端点的访问均为denyAll(), 可以在这里通过SpringEL表达式改变为permitAll()
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }
}
