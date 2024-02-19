package com.spring.security.chp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class   ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.formLogin().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest()
//                .authenticated()
                .hasAnyAuthority("write");
//                 .permitAll();
//                 .denyAll();
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager userDetailsService =
//                new InMemoryUserDetailsManager();
//        UserDetails user = new DefaultUser();
//
//        userDetailsService.createUser(user);
//        return userDetailsService;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager userDetailsService =
//                new InMemoryUserDetailsManager();
//
//        UserDetails user1 = new DumbUser("kamil", "12345");
//        UserDetails user2 = new DumbUser("isa", "123");
//        UserDetails user3 = new DumbUser("farhad", "12");
//
//        userDetailsService.createUser(user1);
//        userDetailsService.createUser(user2);
//        userDetailsService.createUser(user3);
//
//        return userDetailsService;
//
//    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        JdbcUserDetailsManager userDetailsManager =
                new JdbcUserDetailsManager(dataSource);

//        InMemoryUserDetailsManager userDetailsService =
//                new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("kamil1")
//                .password("limak")
                .password(passwordEncoder().encode("limak"))
                .authorities("read") //buna diqqet elemek lazimdir diger koddada bunu arasdir
                .build();

        JdbcUserDetailsManager userDetailsManager1 =
                new JdbcUserDetailsManager(dataSource);

//        InMemoryUserDetailsManager userDetailsService1 =
//                new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("akkamil1")
                .password("kamil")
//                .password(passwordEncoder().encode("limakka"))
                .authorities("write")
                .build();

        userDetailsManager.createUser(user);
        userDetailsManager1.createUser(user1);

        return  userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
