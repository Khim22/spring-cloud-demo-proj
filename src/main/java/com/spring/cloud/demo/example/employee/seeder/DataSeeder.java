package com.spring.cloud.demo.example.employee.seeder;


import com.spring.cloud.demo.example.employee.model.Employee;
import com.spring.cloud.demo.example.employee.repository.EmployeeRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    private final EmployeeRepository employeeRepository;

    public DataSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        System.out.println(context.getDisplayName());
    }

    @EventListener(value = ContextRefreshedEvent.class)
    public void init(ContextRefreshedEvent event) {
        System.out.println("Dataseeder init");
        if(event.getApplicationContext().getClass().equals(AnnotationConfigServletWebServerApplicationContext.class))
            this.employeeRepository.save(new Employee(null, "john", "dept")).subscribe( employee -> System.out.println(employee.getId()));
    }
}
