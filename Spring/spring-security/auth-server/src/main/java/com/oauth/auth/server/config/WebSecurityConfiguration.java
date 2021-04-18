package com.oauth.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
            .csrf().disable()
            .authorizeRequests().antMatchers("/login", "/oauth/authorize").permitAll()
                                .anyRequest().authenticated()
        .and().antMatcher("/user/**").httpBasic()
        .and().formLogin();*/

        /*http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/oauth/authorize").authenticated()
                .anyRequest().authenticated()
            .and().antMatcher("/user/**").httpBasic()
        .and().formLogin().permitAll();*/

        http.csrf().disable()
                //.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            //.and()
                .authorizeRequests().antMatchers("/oauth/authorize").authenticated()
            .and()
                .formLogin().permitAll()
            .and()
                .logout()
            .and()
                .authorizeRequests().anyRequest().authenticated()
            .and();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("geno").password(passwordEncoder.encode("123")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
