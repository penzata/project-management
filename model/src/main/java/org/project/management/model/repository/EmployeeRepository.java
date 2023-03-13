package org.project.management.model.repository;

import org.project.management.model.aggregators.EmployeeWithCompletedTasks;
import org.project.management.model.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);

    boolean existsByEmail(String email);

    List<Employee> findAllEmployees();

    List<EmployeeWithCompletedTasks> getTopEmployees(Integer maxNum);

    List<Employee> findAllByProjectId(Long projectId);
}