package org.pro.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;


@Configuration
public class ProjectConfig
        extends WebSecurityConfigurerAdapter {

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

        private ClientRegistration clientRegistration() {
            return CommonOAuth2Provider.FACEBOOK
                    .getBuilder("facebook")
                    .clientId("260233130427984")
                    .clientSecret("41ba62d3e84cd044af4346011bc5b626")
                    .build();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.oauth2Login();
            http.authorizeRequests()
                    .anyRequest()
                    .authenticated();

        }



}

