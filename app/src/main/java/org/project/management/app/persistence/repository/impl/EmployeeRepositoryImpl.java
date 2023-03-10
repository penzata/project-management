package org.project.management.app.persistence.repository.impl;

import lombok.AllArgsConstructor;
import org.project.management.app.persistence.entity.EmployeeEntity;
import org.project.management.app.persistence.repository.EmployeeRepositoryJpa;
import org.project.management.model.model.Employee;
import org.project.management.model.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeRepositoryJpa employeeRepositoryJpa;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntity.fromModel(employee);
        EmployeeEntity savedEmployee = employeeRepositoryJpa.save(employeeEntity);
        return savedEmployee.toModel();
    }

    // todo add custom exception and handler
    @Override
    public Optional<Employee> findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepositoryJpa.findById(id)
                .orElseThrow();
        return Optional.ofNullable(employeeEntity.toModel());
    }

    @Override
    public void deleteById(Long id) {
        employeeRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Employee> findByIds(List<Long> topFiveEmployeeIds) {
        return employeeRepositoryJpa.findAllByIdEquals(topFiveEmployeeIds).stream()
                .map(EmployeeEntity::toModel)
                .toList();
    }
}