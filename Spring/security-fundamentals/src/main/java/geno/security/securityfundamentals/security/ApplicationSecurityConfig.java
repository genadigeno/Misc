package geno.security.securityfundamentals.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static geno.security.securityfundamentals.security.UserPermission.COURSE_WRITE;
import static geno.security.securityfundamentals.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "index", "/css/**", "/js/**").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest().authenticated()
            .and().httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails1 = User.builder().username("geno")
                                                 .password(passwordEncoder.encode("123"))
//                                                 .roles(STUDENT.name())
                                                 .authorities(STUDENT.getGrantedAuthorities())
                                                 .build();

        UserDetails userDetails2 = User.builder().username("lynda")
                                                 .password(passwordEncoder.encode("123"))
//                                                 .roles(ADMIN.name())
                                                 .authorities(ADMIN.getGrantedAuthorities())
                                                 .build();

        UserDetails userDetails3 = User.builder().username("john")
                                                 .password(passwordEncoder.encode("123"))
//                                                 .roles(ADMINTRAINEE.name())
                                                 .authorities(ADMINTRAINEE.getGrantedAuthorities())
                                                 .build();

        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
