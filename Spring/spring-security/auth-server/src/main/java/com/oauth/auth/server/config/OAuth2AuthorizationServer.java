package com.oauth.auth.server.config;

import com.oauth.auth.server.util.Constant;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    /**
     * Client Credentials grant type
     * იგი შესაფერისია როცა აპლიკაციას სჭირდება წვდომა რესურსებზე თავისით
     * და არა წვდომა რესურსის სერვერზე.
     * */

    /*@Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("clientapp");
        tokenServices.setClientSecret("{noop}123456");
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/token");
        return tokenServices;
    }

    @Bean
    public ClientDetailsService clientDetailsService(){
        *//*InMemoryClientDetailsService clientDetailsService = new InMemoryClientDetailsService();
        clientDetailsService.loadClientByClientId();
        clientDetailsService.setClientDetailsStore();*//*
        return new ClientDetailsService() {
            @Override
            public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
                BaseClientDetails details = new BaseClientDetails();
                details.setClientId("clientapp");
                details.setAuthorizedGrantTypes(Arrays.asList("authorization_code"));
                details.setScope(Arrays.asList("read_profile, read_contacts"));
                details.setRegisteredRedirectUri(Collections.singleton("http://localhost:9000/callback"));
                details.setResourceIds(Arrays.asList("crypto-portfolio"));
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                details.setAuthorities(authorities);
                return details;
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenService());
        authenticationManager.setResourceId("crypto-portfolio");
        authenticationManager.setClientDetailsService(clientDetailsService());
        return authenticationManager;
    }*/

    @Bean
    public ClientRegistrationService clientRegistrationService() { return new JdbcClientDetailsService(dataSource); }

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.authenticationManager(authenticationManager());
        //endpoints.userDetailsService(new InMemoryUserDetailsManager());
        //super.configure(endpoints);

        endpoints.approvalStore(approvalStore())
                 .tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients.inMemory().withClient("clientapp")
                            .secret("{noop}123456")
                            .redirectUris("http://localhost:9000/callback")
                            .authorizedGrantTypes(
                                Constant.GRANT_TYPE.AUTHORIZATION_CODE,
                                Constant.GRANT_TYPE.REFRESH_TOKEN,
                                Constant.GRANT_TYPE.PASSWORD
                            ).accessTokenValiditySeconds(120)
//                            .authorizedGrantTypes(Constant.GRANT_TYPE.CLIENT_CREDENTIALS)
                            .scopes("read_profile", "read_contacts");*/

        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        super.configure(security);
        security.passwordEncoder(passwordEncoder());
    }
}
