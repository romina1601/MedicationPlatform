package com.example.springdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class EndpointsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        String USER_CREDENTIALS_QUERY = "select username as principal, password as credentials, true from users where username=?";
        String USER_AUTHORITIES_QUERY = "select username as principal, role as role from users where username=?";



        auth.jdbcAuthentication().passwordEncoder(getPasswordEncoder()).dataSource(dataSource)
                .usersByUsernameQuery(USER_CREDENTIALS_QUERY)
                .authoritiesByUsernameQuery(USER_AUTHORITIES_QUERY)
                .rolePrefix("ROLE_");
    }


    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/doctor/**").hasRole("DOCTOR")
                .antMatchers("/users/**").hasRole("DOCTOR")
                //.antMatchers("/users/**").permitAll()
                //.antMatchers("/doctor/**").permitAll()
                .antMatchers("/medicationPlan/**").hasRole("DOCTOR")
                .antMatchers("/medication/**").hasRole("DOCTOR")
                .antMatchers("/caregiver/**").hasAnyRole("CAREGIVER", "DOCTOR")
                .antMatchers("/patient/**").hasAnyRole("PATIENT", "DOCTOR")
                .antMatchers("/login").hasAnyRole("DOCTOR", "CAREGIVER", "PATIENT").anyRequest().permitAll()
                .and()
                .httpBasic();
    }
}
