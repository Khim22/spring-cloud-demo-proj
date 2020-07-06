package com.spring.cloud.demo.example.employee.exchange;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

public interface EmployeeSource {
    @Output("employeeOutput")
    MessageChannel employeeOutput();
}
