package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
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
}
