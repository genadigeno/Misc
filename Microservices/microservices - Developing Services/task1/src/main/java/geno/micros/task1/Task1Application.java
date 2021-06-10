package geno.micros.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class Task1Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

	@Bean
	public TollProcessingTask tollProcessingTask(){
		return new TollProcessingTask();
	}
}
