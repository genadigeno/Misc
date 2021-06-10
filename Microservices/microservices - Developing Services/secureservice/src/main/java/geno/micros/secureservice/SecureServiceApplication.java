package geno.micros.secureservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@SpringBootApplication
public class SecureServiceApplication {

	@Autowired
	private ResourceServerProperties sso;

	@Bean
	public ResourceServerTokenServices resourceServerTokenServices(){
		return new CustomUserInfoTokenService(sso.getUserInfoUri(), sso.getClientId());
	}

	public static void main(String[] args) {
		SpringApplication.run(SecureServiceApplication.class, args);
	}
}
