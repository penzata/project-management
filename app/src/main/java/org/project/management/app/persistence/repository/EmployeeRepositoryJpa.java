package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.EmployeeEntity;
import org.project.management.model.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<EmployeeEntity, Long> {

    boolean existsByEmail(String email);

    List<Employee> findAllByProjectId(Long projectId);
}