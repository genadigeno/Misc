package geno.micro.tollratebillboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class TollRateBillboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollRateBillboardApplication.class, args);
	}

}
