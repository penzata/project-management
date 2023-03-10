package org.project.management.model.service;

import org.project.management.model.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployee(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<Employee> findByIds(List<Long> topFiveEmployeeIds);
}
