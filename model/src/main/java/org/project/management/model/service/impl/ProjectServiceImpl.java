package org.project.management.model.service.impl;

import org.project.management.model.model.Project;
import org.project.management.model.repository.ProjectRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.ProjectService;
import org.project.management.model.service.TaskService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskService taskService, EmployeeService employeeService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectOrThrow(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project projectToUpdate = projectRepository.findById(id);

        Project updatedProject = projectToUpdate.updateProjectInfo(project);
        return projectRepository.save(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
        taskService.deleteByProjectId(id);
        employeeService.unassignAllFromProject(id);
    }

}