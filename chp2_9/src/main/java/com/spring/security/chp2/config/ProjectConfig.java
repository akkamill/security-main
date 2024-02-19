package com.spring.security.chp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    private final Map<String, String> userDetails = new HashMap<>();


    public void user(String username, String password) {
        String hashedPassword = passwordEncoder().encode(password);
        userDetails.put(username, hashedPassword);
    }

    public boolean authenticatedUser(String username, String password) {

        if (userDetails.containsKey(username)) {
            String checkedPassword = userDetails.get(username);
            return passwordEncoder().matches(password, checkedPassword);
        }
        return false;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        user("kamil", "kamil");
        user("isa", "123");
        user("farhad", "12");

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if (userDetails.containsKey(username)) {
                    String password = userDetails.get(username);
                    return org.springframework.security.core.userdetails.User
                            .withUsername(username)
                            .password(password)
                            .roles("USER")
                            .build();
                }
                return null;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}
