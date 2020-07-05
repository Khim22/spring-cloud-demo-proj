package com.spring.cloud.demo.example.employee.service;

import com.spring.cloud.demo.example.employee.model.Employee;
import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = new Employee(employeeDto.getEmployeeId(), employeeDto.getEmployeeName(), employeeDto.getDepartment());
        Employee saved = employeeRepository.save(newEmployee);
        return new EmployeeDto(saved.getId(), saved.getName(), saved.getDepartment());
    }
}
