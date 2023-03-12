package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<EmployeeEntity, Long> {

    boolean existsByEmail(String email);
}