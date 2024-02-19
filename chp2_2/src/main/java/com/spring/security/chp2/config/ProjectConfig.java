package com.spring.security.chp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsService =
                new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("kamil")
//                .password("limak")
                .password(passwordEncoder().encode("limak"))
                .authorities("read") //buna diqqet elemek lazimdir diger koddada bunu arasdir
                .build();

        InMemoryUserDetailsManager userDetailsService1 =
                new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("akkamil")
                .password("kamil")
//                .password(passwordEncoder().encode("limakka"))
                .authorities("read")
                .build();

        userDetailsService.createUser(user);
        userDetailsService.createUser(user1);

        return  userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
