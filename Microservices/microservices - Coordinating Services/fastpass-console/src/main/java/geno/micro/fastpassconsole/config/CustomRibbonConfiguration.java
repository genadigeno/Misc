package geno.micro.fastpassconsole.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRibbonConfiguration {

   /* @Autowired
    //@Qualifier("")
    IClientConfig ribbonConfig;
*/
    @Bean
    public IRule ribbonRule() {
        return new WeightedResponseTimeRule();
    }
}
