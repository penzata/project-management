package org.project.management.app.persistence.repository.impl;

import lombok.AllArgsConstructor;
import org.project.management.app.persistence.entity.ProjectEntity;
import org.project.management.app.persistence.repository.ProjectRepositoryJpa;
import org.project.management.model.exception.IdNotFoundException;
import org.project.management.model.model.Project;
import org.project.management.model.repository.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectRepositoryJpa projectRepositoryJpa;

    @Override
    public Project save(Project project) {
        ProjectEntity projectEntity = ProjectEntity.fromModel(project);
        ProjectEntity savedProject = projectRepositoryJpa.save(projectEntity);
        return savedProject.toModel();
    }

    @Override
    public Project findById(Long id) {
        ProjectEntity projectEntity = projectRepositoryJpa.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));
        return projectEntity.toModel();
    }

    @Override
    public void deleteById(Long id) {
        projectRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Project> findAll() {

        return projectRepositoryJpa.findAll().stream()
                .map(ProjectEntity::toModel)
                .toList();
    }
}