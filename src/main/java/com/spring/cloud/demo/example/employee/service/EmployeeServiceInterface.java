package com.spring.cloud.demo.example.employee.service;

import com.spring.cloud.demo.example.employee.model.EmployeeDto;

public interface EmployeeServiceInterface {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
