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
        http.formLogin();
        http.csrf();
//                .authenticated()
//        String expression =
//                "hasAnyAuthority('read', 'write') and !hasAuthority('delete')";
        http.authorizeRequests()
//                .anyRequest()
                .mvcMatchers("/ciao").hasRole("ADMIN")
                .mvcMatchers("/hello").hasRole("MANAGER");
//                .access(expression);
//                 .permitAll();
//                 .denyAll();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager =
                new InMemoryUserDetailsManager();


        UserDetails user = User.withUsername("Tofiq")
//                .password("limak")
                .password(passwordEncoder().encode("12345"))
//                .authorities("ROLE_ADMIN") //buna diqqet elemek lazimdir diger koddada bunu arasdir
                .roles("ADMIN")
                .build();


        UserDetails user1 = User.withUsername("Tamella")
                .password("kamil")
//                .password(passwordEncoder().encode("limakka"))
//                .authorities("MANAGER")
                .roles("MANAGER")
                .build();

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(user1);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
