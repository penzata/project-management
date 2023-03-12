package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.project.management.model.model.Project;

@Builder
public record ProjectDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY)
                          Long id,
                         @NotBlank
                          String name,

                          String description) {

    public static ProjectDTO fromModel(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }

    public Project toModel() {
        return Project.project(id, name, description);
    }
}