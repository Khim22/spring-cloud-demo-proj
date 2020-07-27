package com.spring.cloud.demo.example.employee.service;

import com.spring.cloud.demo.example.employee.exchange.EmployeePublisher;
import com.spring.cloud.demo.example.employee.model.Employee;
import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private EmployeeRepository employeeRepository;
    private EmployeePublisher employeePublisher;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeePublisher employeePublisher) {
        this.employeeRepository = employeeRepository;
        this.employeePublisher = employeePublisher;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = Employee.builder()
                .department(employeeDto.getDepartment())
                .name(employeeDto.getEmployeeName())
                .build();
        Mono<Employee> saved = employeeRepository.save(newEmployee)
                .doOnNext( employee -> employeePublisher.publish(employee));
        Employee b = saved.block();

        return new EmployeeDto(String.valueOf(Objects.requireNonNull(b).getId()), b.getName(), b.getDepartment());
    }

    @Override
    public List<EmployeeDto> findAll() {

        return Objects.requireNonNull(employeeRepository.findAll()
                .collectList().block())
                .stream()
                .map(employee -> new EmployeeDto(String.valueOf(employee.getId()), employee.getName(), employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Mono<EmployeeDto> getEmployeeFluxById(String empId) {
        return employeeRepository.findById(Long.valueOf(empId))
                .map(employee ->  new EmployeeDto(empId, employee.getName(), employee.getDepartment()));
    }

    @Override
    public EmployeeDto getEmployeeById(String empId) {
        Employee employee = employeeRepository.findById(Long.valueOf(empId)).block();
        return new EmployeeDto(empId, Objects.requireNonNull(employee).getName(), employee.getDepartment());
    }
}
