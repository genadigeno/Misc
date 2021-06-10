package geno.micros.secureui.controller;

import geno.micros.secureui.TollUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@EnableOAuth2Sso
public class ReportController extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("oauth2ClientContext")
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private OAuth2RestTemplate oauth2RestTemplate;

    @Value("${service.url}")
    private String url;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadHome() {
        return "home";
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String loadReports(Model model) {
        OAuth2AccessToken oauth2AccessToken = oauth2ClientContext.getAccessToken();
        System.out.println("Access Token: " + oauth2AccessToken.getValue());

        ResponseEntity<ArrayList<TollUsage>> data = oauth2RestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayList<TollUsage>>() {});

        model.addAttribute("tolls", data.getBody());
        model.addAttribute("token", oauth2AccessToken.getValue());
        return "reports";
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated();
    }
}
