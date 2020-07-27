package com.spring.cloud.demo.example.employee.service;

import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeServiceInterface {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    Mono<EmployeeDto> getEmployeeFluxById(String empId);

    EmployeeDto getEmployeeById(String empId);
}
