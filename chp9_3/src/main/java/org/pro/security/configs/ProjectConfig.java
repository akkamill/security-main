package org.pro.security.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private StaticKeyAuthenticationFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                        new RequestValidationFilter(),
//                        filter,
                        BasicAuthenticationFilter.class)
//                .addFilterAfter(
//                .addFilterAt(filter, BasicAuthenticationFilter.class)
//                        new AuthenticationLoggingFilter(),
//                        BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}