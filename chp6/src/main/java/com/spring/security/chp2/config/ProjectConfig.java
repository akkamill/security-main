package com.spring.security.chp2.config;

import com.spring.security.chp2.service.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProviderService authenticationProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return new SCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/main", true);
        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.formLogin();
//        http.csrf();
////                .authenticated()
////        String expression =
////                "hasAnyAuthority('read', 'write') and !hasAuthority('delete')";
//        http.authorizeRequests()
//                .anyRequest().hasAuthority("Search");
//
////                .anyRequest()
////                .mvcMatchers("/admin/read", "/admin/write", "/admin/delete").hasRole("ADMIN")
////                .mvcMatchers("/manager/read", "/manager/write").hasRole("MANAGER");
////                .access(expression);
////                 .permitAll();
////                 .denyAll();
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//
//        JdbcUserDetailsManager userDetailsManager =
//                new JdbcUserDetailsManager(dataSource);
//
//
//        UserDetails user = User.withUsername("Tofiq")
////                .password("limak")
//                .password(passwordEncoder().encode("12345"))
//                .authorities("ROLE_ADMIN") //buna diqqet elemek lazimdir diger koddada bunu arasdir
////                .roles("update")
//                .build();
//
//        JdbcUserDetailsManager userDetailsManager1 =
//                new JdbcUserDetailsManager(dataSource);
//
//
//        UserDetails user1 = User.withUsername("Tamella")
//                .password("kamil")
////                .password(passwordEncoder().encode("limakka"))
//                .authorities("MANAGER")
////                .roles("delete")
//                .build();
//
//        userDetailsManager.createUser(user);
//        userDetailsManager1.createUser(user1);
//
//        return userDetailsManager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//}
