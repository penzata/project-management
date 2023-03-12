package org.project.management.model.repository;

import org.project.management.model.model.Project;

import java.util.List;

public interface ProjectRepository {

    Project save(Project employee);

    Project findById(Long id);

    void deleteById(Long id);

    List<Project> findAll();

}