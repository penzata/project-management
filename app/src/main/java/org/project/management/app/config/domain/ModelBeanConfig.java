package org.project.management.app.config.domain;

import org.project.management.model.repository.EmployeeRepository;
import org.project.management.model.repository.ProjectRepository;
import org.project.management.model.repository.TaskRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.ProjectService;
import org.project.management.model.service.TaskService;
import org.project.management.model.service.impl.EmployeeServiceImpl;
import org.project.management.model.service.impl.ProjectServiceImpl;
import org.project.management.model.service.impl.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ModelBeanConfig {

    @Bean
    EmployeeService employeeService(@Lazy TaskService taskService, EmployeeRepository employeeRepository, @Lazy ProjectService projectService) {
        return new EmployeeServiceImpl(taskService, projectService, employeeRepository);
    }

    @Bean
    TaskService taskService(@Lazy EmployeeService employeeService, TaskRepository taskRepository, @Lazy ProjectService projectService) {
        return new TaskServiceImpl(employeeService, projectService, taskRepository);
    }

    @Bean
    ProjectService projectService(ProjectRepository projectRepository, TaskService taskService, @Lazy EmployeeService employeeService) {
        return new ProjectServiceImpl(projectRepository, taskService, employeeService);
    }
}