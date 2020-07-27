package com.spring.cloud.demo.example.employee.repository;

import com.spring.cloud.demo.example.employee.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {
}
