package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findAllByIdIn(List<Long> ids);

    boolean existsByEmail(String email);
}