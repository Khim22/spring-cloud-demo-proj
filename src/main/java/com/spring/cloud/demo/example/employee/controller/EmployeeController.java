package com.spring.cloud.demo.example.employee.controller;

import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.service.EmployeeService;
import com.spring.cloud.demo.example.employee.service.EmployeeServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceInterface employeeService;

    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return  employeeService.createEmployee(employeeDto);
    }
}
