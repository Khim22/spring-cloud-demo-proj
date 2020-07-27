package com.spring.cloud.demo.example.employee.controller;

import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.service.EmployeeService;
import com.spring.cloud.demo.example.employee.service.EmployeeServiceInterface;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @GetMapping
    public List<EmployeeDto> findAllEmployees(){
        return  employeeService.findAll();
    }

    @GetMapping(value = "/{id}", params = "flux")
    public Mono<EmployeeDto> getEmployeeMono(@PathVariable("id") String employeeId, @RequestParam("flux") boolean isFlux){
        if(isFlux)
            return employeeService.getEmployeeFluxById(employeeId);
        else
            return null;
    }

    @GetMapping(value = "/{id}", params = "block")
    public EmployeeDto getEmployee(@PathVariable("id") String employeeId, @RequestParam boolean block){
        if(block)
            return employeeService.getEmployeeById(employeeId);
        else
            return null;
    }
}
