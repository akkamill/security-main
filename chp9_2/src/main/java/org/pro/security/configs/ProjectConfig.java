package org.pro.security.configs;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * HTTP Basic authentication doesn’t fit into most application architectures.
 * Sometimes we’d like to change it to match our appli- cation.
 * Similarly, not all endpoints of an application need to be secured, and for those that do,
 * we might need to choose different authorization rules.
 * To make such changes, we start by extending the WebSecurityConfigurerAdapter class.
 * Extending this class allows us to override the configure(HttpSecurity http) method as presented in the next listing
 */

// TODO: 1/6/22 WebSecurityConfigurerAdapter basqa hansi classlar var buna bax 

/**
 * avoid mixing the two approaches to keep the code clean and easier to understand.
 * Using the AuthenticationManagerBuilder, you can configure users for authenti- cation directly.
 * It creates the UserDetailsService for you in this case.
 * The syntax, however, becomes even more complex and could be considered difficult to read
 * I’ve seen this choice more than once, even with production-ready systems.
 */

@Configuration
public class ProjectConfig {
    @Autowired
    private StaticKeyAuthenticationFilter filter;

//    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService =
                new InMemoryUserDetailsManager();
        UserDetails user= User.withUsername("user")
                .password("123")
                .roles("ADMIN")
                .build();
       userDetailsService.createUser(user);
       return userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.formLogin();
//        http.httpBasic();
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
//                        .mvcMatchers("/hello").hasRole("ADMIN");
                .anyRequest()
//                        .hasRole("ADMIN")
//                        .hasAuthority("read");
//                        .hasAnyAuthority("read","write");
                        .permitAll();
        return http.build();
    }
}
