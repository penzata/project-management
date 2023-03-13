package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.project.management.model.model.Task;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record TaskDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      Long id,
                      @NotBlank
                      String title,
                      String description,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      Long assigneeId,
                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                      LocalDateTime dueDate,
                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                      LocalDateTime completedDate,
                      @NotNull
                      Long projectId) {

    public static TaskDTO fromModel(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .assigneeId(task.getAssigneeId().orElse(null))
                .dueDate(task.getDueDate().orElse(null))
                .completedDate(task.getCompletedDate().orElse(null))
                .projectId(task.getProjectId())
                .build();
    }

    public Task toModel() {
        return Task.task(id, title, description, assigneeId, dueDate, completedDate, projectId);
    }
}
