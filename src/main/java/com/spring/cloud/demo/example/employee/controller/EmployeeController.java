package com.spring.cloud.demo.example.employee.controller;

import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return  employeeDto;
    }
}
