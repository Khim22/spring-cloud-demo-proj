package com.spring.cloud.demo.example.employee.exchange;

import com.spring.cloud.demo.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(EmployeeSource.class)
public class EmployeePublisher {

    private EmployeeSource employeeSource;

    public EmployeePublisher(EmployeeSource employeeSource) {
        this.employeeSource = employeeSource;
    }

    public Employee publish(Employee employee){
        boolean send = employeeSource.employeeOutput().send(MessageBuilder.withPayload(employee).build());
        System.out.println(send);
        return employee;
    }
}
