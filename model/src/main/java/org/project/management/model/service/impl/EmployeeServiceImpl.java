package org.project.management.model.service.impl;

import org.project.management.model.exception.ExistingEmailException;
import org.project.management.model.model.Employee;
import org.project.management.model.aggregators.EmployeeWithCompletedTasks;
import org.project.management.model.model.Project;
import org.project.management.model.repository.EmployeeRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.ProjectService;
import org.project.management.model.service.TaskService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final TaskService taskService;

    private final ProjectService projectService;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(TaskService taskService, ProjectService projectService, EmployeeRepository employeeRepository) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ExistingEmailException(employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {

        return findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    private Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeToUpdate = findById(id);

        Employee updatedEmployee = employeeToUpdate.updatePersonalInfo(employee);
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        findById(id);
        employeeRepository.deleteById(id);
        taskService.unassignAllFromEmployee(id);
    }

    @Override
    public List<EmployeeWithCompletedTasks> getTopEmployees(String maxNum) {
        return employeeRepository.getTopEmployees(maxNum);
    }

    @Override
    public void unassignAllFromProject(Long projectId) {
        employeeRepository.findByProjectId(projectId)
                .forEach(emp -> {
                    emp.unassignFromProject();
                    employeeRepository.save(emp);
                });
    }

    @Override
    public Employee assignToProject(Long id, Long projectId) {
        Employee employee = employeeRepository.findById(id);
        Project project = projectService.getProjectOrThrow(projectId);

        employee.assignToProject(project);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee unassignFromProject(Long id) {
        Employee employee = employeeRepository.findById(id);
        employee.unassignFromProject();

        return employeeRepository.save(employee);
    }

}