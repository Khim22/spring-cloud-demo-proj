package com.spring.cloud.demo.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.demo.example.employee.controller.EmployeeController;
import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.service.EmployeeServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceInterface employeeService;

    @InjectMocks
    private EmployeeController employeeController = new EmployeeController(employeeService);

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupMockMvc(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void createEmployee_givenEmployeeDto_shouldReturnEmployeeDto() throws Exception {
        // arrange
        EmployeeDto wick = new EmployeeDto("0000", "John Wick", "dept1");
        when(employeeService.createEmployee(wick))
                .thenReturn(wick);

        // act, assert
        mockMvc.perform(post( "/employee")
                .contentType("application/json")
                .content("{\"employeeId\": \"0000\", \"employeeName\":\"John Wick\", \"department\":\"dept1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(wick)));

    }

    @Test
    public void findAllEmployees_shouldReturnEmployeeDtoList() throws Exception {
        // arrange
        EmployeeDto wick = new EmployeeDto("0000", "John Wick", "dept1");
        EmployeeDto zero = new EmployeeDto("0001", "Zero", "dept1");
        when(employeeService.findAll())
                .thenReturn(Arrays.asList(wick, zero));

        // act, assert
        mockMvc.perform(get( "/employee"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Arrays.asList(wick, zero))));

    }

    @Test
    public void getEmployee_givenEmployeeIdAndBlock_shouldReturnEmployeeDto() throws Exception {
        // arrange
        EmployeeDto wick = new EmployeeDto("0", "John Wick", "dept1");
        when(employeeService.getEmployeeById(wick.getEmployeeId()))
                .thenReturn(wick);

        // act, assert
        mockMvc.perform(get( "/employee/0")
                .queryParam("block", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(wick)));

    }
}
