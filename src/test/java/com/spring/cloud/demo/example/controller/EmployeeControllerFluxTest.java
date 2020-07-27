package com.spring.cloud.demo.example.controller;

import com.spring.cloud.demo.example.employee.controller.EmployeeController;
import com.spring.cloud.demo.example.employee.model.EmployeeDto;
import com.spring.cloud.demo.example.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@WebFluxTest(EmployeeController.class)
@ExtendWith(SpringExtension.class)
public class EmployeeControllerFluxTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployeeMono_givenEmployeeIdAndFlux_shouldReturnEmployeeDtoFlux() {
        // arrange
        EmployeeDto wick = new EmployeeDto("0", "John Wick", "dept1");
        when(employeeService.getEmployeeFluxById(wick.getEmployeeId()))
                .thenReturn(Mono.just(wick));

        // act, assert
        FluxExchangeResult<EmployeeDto> result = webTestClient.get()
                .uri(uriBuilder ->  uriBuilder
                        .path("/employee/0")
                        .queryParam("flux", true)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(EmployeeDto.class);

        // assert
        StepVerifier.create(result.getResponseBody())
                .expectNext(wick)
                .thenCancel()
                .verify();

    }
}
