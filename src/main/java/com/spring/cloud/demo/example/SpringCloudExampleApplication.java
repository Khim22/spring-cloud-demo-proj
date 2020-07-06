package com.spring.cloud.demo.example;

import com.spring.cloud.demo.example.employee.exchange.EmployeeSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
public class SpringCloudExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudExampleApplication.class, args);
	}

}
