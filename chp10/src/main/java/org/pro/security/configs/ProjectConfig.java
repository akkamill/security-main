package org.pro.security.configs;


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
//        extends WebSecurityConfigurerAdapter{
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService =
                new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("user")
                .password("123")
                .authorities("read") //buna diqqet elemek lazimdir diger koddada bunu arasdir
                .build();
        userDetailsService.createUser(user);
        return  userDetailsService;
    }

//    @Override
//    protected void configure(HttpSecurity http)  throws Exception {
//      http.addFilterAfter(
//              new CsrfTokenLogger(), CsrfFilter.class)
//                .authorizeRequests()
//                .anyRequest().permitAll();
//  }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
//        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                http.authorizeRequests()
//                        .mvcMatchers("/hello").hasRole("ADMIN");
                .anyRequest()
//                        .hasRole("ADMIN")
//                        .hasAuthority("read");
//                        .hasAnyAuthority("read","write");
                .permitAll();
        return http.build();
    }

   @Bean
   public PasswordEncoder passwordEncoder() {
       return NoOpPasswordEncoder.getInstance();
   }

}
/**
 * You use CSRF protection for web apps running in a browser, 
 * where you should expect that mutating operations can be done by the browser 
 * that loads the displayed content of the app.
*/