package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, Long> {

}