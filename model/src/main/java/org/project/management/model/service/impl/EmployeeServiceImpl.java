package org.project.management.model.service.impl;

import org.project.management.model.model.Employee;
import org.project.management.model.repository.EmployeeRepository;
import org.project.management.model.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {

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

        Employee updatedEmployee = employeeToUpdate.updateAttributes(employee);
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
}