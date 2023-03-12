package org.project.management.model.repository;

import org.project.management.model.model.Employee;
import org.project.management.model.aggregators.EmployeeWithCompletedTasks;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);

    boolean existsByEmail(String email);

    List<Employee> findAllEmployees();

    List<EmployeeWithCompletedTasks> getTopEmployees(String maxNum);

    List<Employee> findByProjectId(Long projectId);
}