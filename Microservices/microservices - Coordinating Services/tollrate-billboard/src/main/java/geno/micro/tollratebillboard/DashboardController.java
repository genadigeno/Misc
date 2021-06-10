package geno.micro.tollratebillboard;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/dashboard")
	@HystrixCommand(fallbackMethod = "getTollRateBackUp")
	public ResponseEntity<?> GetTollRate(@RequestParam int stationId) {
		System.out.println("stationId: " + stationId);

		TollRate tr = restTemplate.getForObject("http://toll-service/tollrate/" + stationId, TollRate.class);
		System.out.println("TollRate: " + tr);
		return ResponseEntity.ok(tr);
	}

	public ResponseEntity<?> getTollRateBackUp(@RequestParam Integer stationId) {
		System.out.println("stationId = " + stationId);
		return return ResponseEntity.status(500).body("Service is unavailable");;
	}
}
