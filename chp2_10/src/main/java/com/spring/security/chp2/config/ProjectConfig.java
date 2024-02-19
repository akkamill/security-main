package com.spring.security.chp2.config;

import com.spring.security.chp2.entity.DumbUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class   ProjectConfig {


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        JdbcUserDetailsManager userDetailsManager =
                new JdbcUserDetailsManager(dataSource);

        UserDetails user = new DumbUser("kamil2", passwordEncoder().encode("123"));
//        UserDetails user1 = new DumbUser("isa", "12345");
//        UserDetails user2 = new DumbUser("farhad", "12345");

//        userDetailsManager.createUser(user);
//        userDetailsManager.createU
//        ser(user1);
//        userDetailsManager.createUser(user2);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PlainText.getInstance();
    }

}
