package org.project.management.app.config.domain;

import org.project.management.model.repository.EmployeeRepository;
import org.project.management.model.repository.TaskRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.TaskService;
import org.project.management.model.service.impl.EmployeeServiceImpl;
import org.project.management.model.service.impl.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ModelBeanConfig {

    @Bean
    EmployeeService employeeService(@Lazy TaskService taskService, EmployeeRepository employeeRepository) {
        return new EmployeeServiceImpl(taskService, employeeRepository);
    }

    @Bean
    TaskService taskService(@Lazy EmployeeService employeeService, TaskRepository taskRepository) {
        return new TaskServiceImpl(employeeService, taskRepository);
    }
}