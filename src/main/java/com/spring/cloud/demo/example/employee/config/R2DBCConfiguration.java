package com.spring.cloud.demo.example.employee.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {
    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .inMemory("testdb")
                        .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
                        .username("sa")
                        .build()
        );
    }
}
