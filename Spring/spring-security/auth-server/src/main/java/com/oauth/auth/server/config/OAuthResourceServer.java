package com.oauth.auth.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * იმისათვის რომ გვქონდეს წვდომა მომხმარებლის რესურსებზე,
 * ჩვენ გვჭირდება შევქმნათ რესურსების სერვერის კონფიგურაცია.
 * */
@Configuration
@EnableResourceServer
public class OAuthResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    /**
     * და დავიცვათ მომხმარებლის პროფილის URL შემდეგი კონფიგურაციის დამატებით:
     * */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated()
        .and()
            .requestMatchers().antMatchers("/api/**");
    }
}
