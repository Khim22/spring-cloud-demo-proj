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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
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

}
