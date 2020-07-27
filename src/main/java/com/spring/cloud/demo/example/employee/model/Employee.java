package com.spring.cloud.demo.example.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    @Id
    @Generated
    private Long id;
    private String name;
    private String department;
}
