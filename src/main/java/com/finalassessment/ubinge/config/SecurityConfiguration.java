package com.finalassessment.ubinge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//       authentication.getName();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
                .antMatchers("/customers/**").access("hasAnyAuthority('ADMIN', 'CUSTOMER')")
                .antMatchers("/restaurantowners/**").access("hasAnyAuthority('ADMIN', 'OWNER')")
                .antMatchers("/restaurants/**/").access("hasAnyAuthority('ADMIN', 'OWNER', 'RESTRO')")
                .antMatchers("/deliveryguys/**").access("hasAnyAuthority('ADMIN', 'DELIVERY')")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint);


        http.addFilterAfter(new CustomFilter(),
                BasicAuthenticationFilter.class);
    }
}
