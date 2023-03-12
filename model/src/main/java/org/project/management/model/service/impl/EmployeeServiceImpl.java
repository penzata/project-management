package org.project.management.model.service.impl;

import org.project.management.model.exceptions.ExistingEmailException;
import org.project.management.model.model.Employee;
import org.project.management.model.repository.EmployeeRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.TaskService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final TaskService taskService;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(TaskService taskService, EmployeeRepository employeeRepository) {
        this.taskService = taskService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ExistingEmailException();
        }
        return employeeRepository.save(employee);
    }

    // todo add custom exception and handler
    @Override
    public Employee getEmployee(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow();
    }

    // todo add custom exception and handler
    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow();

        Employee updatedEmployee = employeeToUpdate.updatePersonalInfo(employee);
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByIds(List<Long> topFiveEmployeeIds) {
        return employeeRepository.findByIds(topFiveEmployeeIds);
    }

    @Override
    public List<Employee> getTopFiveEmployeesByCompletedTasksInPastMonth() {
        //todo works for now, later change to user aggregation in the repository
        List<Long> topFiveEmployeeIds = taskService.getTopFiveEmployeeIdsInPastMonth();
        return employeeRepository.findByIds(topFiveEmployeeIds);
    }
}