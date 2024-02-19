package com.spring.security.chp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.formLogin().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
//                 .permitAll();
//                 .denyAll();
        return http.build();
    }
}
