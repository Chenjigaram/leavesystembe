package com.infy.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages="com.infy.leave")
@PropertySource(value= {"classpath:configuration.properties"})
public class LeaveSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveSystemApplication.class, args);
	}
}
