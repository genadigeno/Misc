package geno.security.securityfundamentals.security;

//import geno.security.securityfundamentals.auth.ApplicationUser;
//import geno.security.securityfundamentals.auth.ApplicationUserService;
import geno.security.securityfundamentals.jwt.JwtConfig;
import geno.security.securityfundamentals.jwt.JwtTokenVerifier;
import geno.security.securityfundamentals.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import static geno.security.securityfundamentals.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("applicationUserService")
    private UserDetailsService applicationUserService;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/", "index", "/css/**", "/js/**").permitAll()
            .antMatchers("/api/**").hasRole(STUDENT.name())
            /*.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
            .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
            .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
            .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())*/
            .anyRequest().authenticated() //
            /*.and().httpBasic()*/
            .and()/*.formLogin()
                .loginPage("/login").permitAll() //
                .defaultSuccessUrl("/courses",true) //
                .passwordParameter("password") //
                .usernameParameter("username") //
            .and().rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)) //
                .key("91f7f53c$837c/46f1~82b9-50f2716799c1") //
                .rememberMeParameter("remember-me") //
            .and().logout()
                .logoutUrl("/logout") //
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true) //
                .invalidateHttpSession(true) //
                .deleteCookies("JSESSIONID", "remember-me") //
                .logoutSuccessUrl("/login")*/
            ;
    }

    /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails geno = User.builder().username("geno")
                                         .password(passwordEncoder.encode("123"))
//                                                 .roles(STUDENT.name())
                                         .authorities(STUDENT.getGrantedAuthorities())
                                         .build();

        UserDetails lynda = User.builder().username("lynda")
                                         .password(passwordEncoder.encode("123"))
//                                                 .roles(ADMIN.name())
                                         .authorities(ADMIN.getGrantedAuthorities())
                                         .build();

        UserDetails john = User.builder().username("john")
                                         .password(passwordEncoder.encode("123"))
//                                                 .roles(ADMINTRAINEE.name())
                                         .authorities(ADMINTRAINEE.getGrantedAuthorities())
                                         .build();*//*
        //ApplicationUser geno = new ApplicationUser(STUDENT.getGrantedAuthorities(), "", "geno");

        return new InMemoryUserDetailsManager(geno, lynda, john);
    }*/

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
