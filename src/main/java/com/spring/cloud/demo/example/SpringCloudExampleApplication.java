package com.spring.cloud.demo.example;

import com.spring.cloud.demo.example.employee.exchange.EmployeeSource;
import com.spring.cloud.demo.example.employee.model.Employee;
import com.spring.cloud.demo.example.employee.repository.EmployeeRepository;
import io.r2dbc.h2.H2Connection;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2Statement;
import io.r2dbc.spi.Connection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class SpringCloudExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudExampleApplication.class, args);
	}

}
