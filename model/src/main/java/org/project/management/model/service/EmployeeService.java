package org.project.management.model.service;

import org.project.management.model.model.Employee;
import org.project.management.model.aggregators.EmployeeWithCompletedTasks;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployee(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<EmployeeWithCompletedTasks> getTopEmployees(String maxNum);
}