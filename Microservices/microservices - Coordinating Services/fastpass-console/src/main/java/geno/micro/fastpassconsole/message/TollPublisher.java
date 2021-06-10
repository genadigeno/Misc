package geno.micro.fastpassconsole.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;

@EnableBinding(TollSource.class)
public class TollPublisher {

    Random r = new Random();

    @Bean
    /*@InboundChannelAdapter(
        channel = "fastpassTollChannel",
        poller = @Poller(fixedDelay = "2000")
    )*/
    public MessageSource<Toll> sendTollCharge(){
        //return "{station: \"20\", customerid: \"100\", timestamp: \"2021-01-02\"}";

        return new MessageSource<Toll>() {
            @Override
            public Message<Toll> receive() {
                return MessageBuilder.withPayload(
                    new Toll("123", "3634","2021"
                )).setHeader("speed", r.nextInt(8) * 10).build();
            }
        };
    }

    class Toll {

        private String stationId;
        private String customerId;
        private String timestamp;

        public Toll(String stationId, String customerId, String timestamp) {
            this.stationId = stationId;
            this.customerId = customerId;
            this.timestamp = timestamp;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
