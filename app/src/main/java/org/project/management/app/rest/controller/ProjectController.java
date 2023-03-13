package org.project.management.app.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.project.management.app.exceptionhandler.Messages;
import org.project.management.app.rest.dto.CustomMessageDTO;
import org.project.management.app.rest.dto.ProjectDTO;
import org.project.management.model.model.Project;
import org.project.management.model.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping()
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects().stream()
                .map(ProjectDTO::fromModel)
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProjectDTO createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.createProject(projectDTO.toModel());
        return ProjectDTO.fromModel(project);
    }

    @GetMapping("/{id}")
    public ProjectDTO getProject(@Min(1) @PathVariable Long id) {
        Project project = projectService.getProjectOrThrow(id);
        return ProjectDTO.fromModel(project);
    }

    @DeleteMapping("/{id}")
    public CustomMessageDTO deleteProject(@Min(1) @PathVariable Long id) {
        projectService.deleteProject(id);
        return CustomMessageDTO.builder()
                .message(Messages.DELETED_PROJECT)
                .build();
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@Min(1) @PathVariable Long id,
                                      @Valid @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.updateProject(id, projectDTO.toModel());
        return ProjectDTO.fromModel(project);
    }
}