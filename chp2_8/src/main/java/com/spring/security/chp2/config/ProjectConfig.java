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

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.formLogin().disable();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated();
////                 .permitAll();
////                 .denyAll();
//        return http.build();
//    }

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
//        UserDetails user3 = new DumbUser("ferhad", "12");
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

        UserDetails user = new DumbUser("kamil", passwordEncoder().encode("123"));
        UserDetails user1 = new DumbUser("isa", "12345");
        UserDetails user2 = new DumbUser("farhad", "12345");

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);

        return userDetailsManager;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager userDetailsService =
//                new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("kamil")
////                .password("limak")
//                .password(passwordEncoder().encode("limak"))
//                .authorities("read") //buna diqqet elemek lazimdir diger koddada bunu arasdir
//                .build();
//
//        InMemoryUserDetailsManager userDetailsService1 =
//                new InMemoryUserDetailsManager();
//        UserDetails user1 = User.withUsername("akkamil")
//                .password("kamil")
////                .password(passwordEncoder().encode("limakka"))
//                .authorities("read")
//                .build();
//
//        userDetailsService.createUser(user);
//        userDetailsService.createUser(user1);
//
//        return  userDetailsService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
