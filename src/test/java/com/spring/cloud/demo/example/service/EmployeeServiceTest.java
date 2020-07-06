package com.spring.cloud.demo.example.service;

import com.spring.cloud.demo.example.employee.exchange.EmployeePublisher;
import com.spring.cloud.demo.example.employee.model.Employee;
import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.repository.EmployeeRepository;
import com.spring.cloud.demo.example.employee.service.EmployeeService;
import com.spring.cloud.demo.example.employee.service.EmployeeServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeePublisher employeePublisher;

    @InjectMocks
    private EmployeeServiceInterface employeeService = new EmployeeService(employeeRepository, employeePublisher);

    @Test
    public void createEmployee_givenEmployeeDto_shouldReturnEmployeeDto() {
        // arrange
        EmployeeDto john = new EmployeeDto("0000", "John Wick", "dept1");
        Employee johnEntity = new Employee("0000", "John Wick", "dept1");
        when(employeeRepository.save(johnEntity)).thenReturn(johnEntity);

        // act
        EmployeeDto result = employeeService.createEmployee(john);

        // assert
        assertThat(result).isEqualTo(john);
    }
}
