package com.sixsixsix516.security.authorization.service;

import com.sixsixsix516.security.client.Client;
import com.sixsixsix516.security.client.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author SUN
 * @date 2022/1/17
 */
@Service
public class OAuthClientDetailService implements ClientDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private ClientDetailsService clientDetailsService;

    @PostConstruct
    public void init() throws Exception {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();

        List<Client> clientList = ClientList.clients;
        for (Client client : clientList) {
            builder.withClient(client.getClientId())
                    // 密钥加密
                    .secret(passwordEncoder.encode(client.getClientSecret()))
                    .scopes(client.getScopes())
                    .authorizedGrantTypes(client.getGrantTypes());
        }
        clientDetailsService = builder.build();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }

}
