package org.project.management.model.service;

import org.project.management.model.model.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project);

    Project getProjectOrThrow(Long id);

    List<Project> getAllProjects();

    Project updateProject(Long id, Project project);

    void deleteProject(Long id);
}