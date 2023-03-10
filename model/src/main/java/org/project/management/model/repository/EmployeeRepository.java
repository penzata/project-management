package org.project.management.model.repository;

import org.project.management.model.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);

    List<Employee> findByIds(List<Long> ids);
}