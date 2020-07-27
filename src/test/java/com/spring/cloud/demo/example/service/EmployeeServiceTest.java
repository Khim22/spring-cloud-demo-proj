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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

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
        EmployeeDto john = new EmployeeDto("0", "John Wick", "dept1");
        Employee johnUnsaved = new Employee(null, "John Wick", "dept1");
        Employee johnEntity = new Employee(0L, "John Wick", "dept1");

        when(employeeRepository.save(johnUnsaved)).thenReturn(Mono.just(johnEntity));

        // act
        EmployeeDto result = employeeService.createEmployee(john);

        // assert
        assertThat(result).isEqualTo(john);
    }

    @Test
    public void findAll_shouldReturnEmployeeDtoList() {
        // arrange
        EmployeeDto john = new EmployeeDto("0", "John Wick", "dept1");
        EmployeeDto zero = new EmployeeDto("1", "Zero", "dept1");

        Employee johnEntity = new Employee(0L, "John Wick", "dept1");
        Employee zeroEntity = new Employee(1L, "Zero", "dept1");
        when(employeeRepository.findAll()).thenReturn(Flux.just(johnEntity, zeroEntity));

        // act
        List<EmployeeDto> result = employeeService.findAll();

        // assert
        assertThat(result).usingRecursiveComparison().isEqualTo(Arrays.asList(john, zero));
    }

    @Test
    public void getEmployeeFluxById_givenEmployeeId_shouldReturnEmployeeDtoFlux() {
        // arrange
        EmployeeDto john = new EmployeeDto("0000", "John Wick", "dept1");
        Employee johnEntity = new Employee(0L, "John Wick", "dept1");

        when(employeeRepository.findById(johnEntity.getId())).thenReturn(Mono.just(johnEntity));

        // act
        Mono<EmployeeDto> result = employeeService.getEmployeeFluxById(john.getEmployeeId());

        // assert
        StepVerifier.create(result)
                .expectNext(john)
                .verifyComplete();
    }

    @Test
    public void getEmployeeById_givenEmployeeId_shouldReturnEmployeeDto() {
        // arrange
        EmployeeDto john = new EmployeeDto("0000", "John Wick", "dept1");
        Employee johnEntity = new Employee(0L, "John Wick", "dept1");

        when(employeeRepository.findById(johnEntity.getId())).thenReturn(Mono.just(johnEntity));

        // act
        EmployeeDto result = employeeService.getEmployeeById(john.getEmployeeId());

        // assert
        assertThat(result).usingRecursiveComparison().isEqualTo(john);
    }


}
