package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.project.management.app.persistence.entity.TaskEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record TaskDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      Long id,
                      @NotBlank
                      String title,
                      @NotBlank
                      String description,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY)
                      Long assigneeId,
                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                      LocalDateTime dueDate) {

    public static TaskDTO fromEntity(TaskEntity taskEntity) {
        return TaskDTO.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .assigneeId(taskEntity.getAssigneeId())
                .dueDate(taskEntity.getDueDate())
                .build();
    }

    public TaskEntity toEntity() {
        return TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .build();
    }
}
