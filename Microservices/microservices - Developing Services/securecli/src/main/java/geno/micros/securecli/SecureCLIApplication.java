package geno.micros.securecli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SecureCLIApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(SecureCLIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting cron job");

		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri("http://localhost:9000/services/oauth/token");
		resourceDetails.setScope(Collections.singletonList("toll_read"));
		resourceDetails.setClientId("local");
		resourceDetails.setClientSecret("localsecretkey");
		resourceDetails.setUsername("geno");
		resourceDetails.setPassword("123");

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
		String token = restTemplate.getAccessToken().getValue();
		System.out.println("token = " + token);
		String response = restTemplate.getForObject("http://localhost:9001/services/tolldata", String.class);
		System.out.println("response = " + response);
	}
}
