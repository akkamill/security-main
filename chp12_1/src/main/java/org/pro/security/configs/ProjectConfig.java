package org.pro.security.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class ProjectConfig
        extends WebSecurityConfigurerAdapter {

//    @Bean
//    public ClientRegistrationRepository clientRepository() {
//        ClientRegistration c = clientRegistration();
//        return new InMemoryClientRegistrationRepository(c);
//    }
//
//        private ClientRegistration clientRegistration() {
//            return CommonOAuth2Provider.GITHUB
//                    .getBuilder("github")
//                    .clientId("325dfb4e926bb1fa5f2e")
//                    .clientSecret("dac918044748c4c431c9798413f8d8797fb9d6ed")
//                    .build();
//        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.oauth2Login();
            http.authorizeRequests()
                    .anyRequest()
                    .authenticated();

        }



}

