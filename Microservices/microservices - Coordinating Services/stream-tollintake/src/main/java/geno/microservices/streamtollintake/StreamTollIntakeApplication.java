package geno.microservices.streamtollintake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Sink.class)
//@EnableBinding(Processor.class)
@SpringBootApplication
public class StreamTollIntakeApplication {

	public static void main(String[] args) { SpringApplication.run(StreamTollIntakeApplication.class, args); }

	//@ServiceActivator(inputChannel = Sink.INPUT)
	//@StreamListener(Processor.INPUT)
	//@SendTo(Processor.OUTPUT)
	@StreamListener(target = Sink.INPUT, condition = "headers['speed'] > 40")
	public void logFast(String msg) {
		System.out.println("fast Message: " + msg);
//		return msg;
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['speed'] <= 40")
	public void logLow(String msg) {
		System.out.println("low Message: " + msg);
	}
}
