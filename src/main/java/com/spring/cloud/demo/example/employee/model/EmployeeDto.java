package com.spring.cloud.demo.example.employee.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@EqualsAndHashCode
public class EmployeeDto {
    private String employeeId;
    private String employeeName;
    private String department;
}
