package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private DataSource dataSource;
    public DataSource getDataSource() {
        return dataSource;
    }*/
    //===============================================================================================================//

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root")
                                     .password(passwordEncoder().encode("root"))
                                     .roles("USER");

        //auth.jdbcAuthentication().dataSource(dataSource);
        //auth.authenticationProvider(authenticationProvider());
    }

    /*@Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;*/

    /*@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        //provider.setUserDetailsService(userDetailsService);
        return provider;
    }*/

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/anonymous*").anonymous()
            .antMatchers("/login*"/*, "/oauth/authorize*"*/).permitAll()
            .antMatchers("/assets/css/**", "/assets/js/**", "/images/**").permitAll()
            .antMatchers("index*").permitAll()
            .anyRequest()/*.authenticated()*/.hasRole("USER")
        /*.and()
            .rememberMe()
            .key("fd87yds7sdf789df8998df")
            .tokenRepository(tokenRepository())*/
        .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/perform_login").and().oauth2Login()
            .defaultSuccessUrl("/", true);
    }

    /*@Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }*/
}
