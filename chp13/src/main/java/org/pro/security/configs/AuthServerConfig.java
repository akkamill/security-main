package org.pro.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    public AuthServerConfig(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(manager);
    }

    @Override
    public void configure(
            ClientDetailsServiceConfigurer clients) throws Exception{
//        var service = new InMemoryClientDetailsService();
//        BaseClientDetails cd = new BaseClientDetails();
//        cd.setClientId("client");
//        cd.setClientSecret("secret");
//        cd.setScope(List.of("read"));
//        cd.setAuthorizedGrantTypes(List.of("password"));
//        service.setClientDetailsStore(Map.of("client", cd));

        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
//                .authorizedGrantTypes("authorization_code")
                .authorizedGrantTypes("password","refresh_token")
//                .accessTokenValiditySeconds(60*15)
                .scopes("read")
//                .and()
//                .withClient("client1")
//                .secret(passwordEncoder.encode("secret1"))
                .and()
                .withClient("resourceclient")
                .secret(passwordEncoder.encode("resourcesecret"));
    }

    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()");
    }

}
