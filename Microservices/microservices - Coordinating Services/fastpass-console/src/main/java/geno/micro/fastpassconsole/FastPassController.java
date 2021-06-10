package geno.micro.fastpassconsole;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import geno.micro.fastpassconsole.config.CustomRibbonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RibbonClient(
	name = "fastpass-service",
	configuration = CustomRibbonConfiguration.class
)
public class FastPassController {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(path="/customerdetails", params={"fastpassid"})
	@HystrixCommand(fallbackMethod = "getFastPassCustomerDetailBackUtp")
	public ResponseEntity<?> getFastPassCustomerDetails(@RequestParam String fastpassid) {
		
		FastPassCustomer c = restTemplate.getForObject("http://fastpass-service/fastpass?fastpassid=" + fastpassid,
																							   FastPassCustomer.class);
		System.out.println("retrieved customer details");

		return ResponseEntity.ok(c);
	}

	public ResponseEntity<?> getFastPassCustomerDetailBackUtp(@RequestParam String fasrpass){
		System.out.println("fasrpass = " + fasrpass);
		return ResponseEntity.status(500).body("Service is unavailable");
	}
}
