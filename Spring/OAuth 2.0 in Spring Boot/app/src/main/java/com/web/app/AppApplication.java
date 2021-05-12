package com.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient webClient(ClientRegistrationRepository clientRepo, OAuth2AuthorizedClientRepository oAuth2Repo) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2AuthorizedClientExchangeFilterFunction =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRepo, oAuth2Repo);
		oAuth2AuthorizedClientExchangeFilterFunction.setDefaultOAuth2AuthorizedClient(true);
		return WebClient.builder().apply(oAuth2AuthorizedClientExchangeFilterFunction.oauth2Configuration()).build();
	}
}
