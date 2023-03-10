package org.project.management.app.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.project.management.model.model.Task;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private Long assigneeId;
    private LocalDateTime dueDate;

    public static TaskEntity fromModel(Task task) {
        return TaskEntity.builder()
                .id(task.getId().orElse(null))
                .title(task.getTitle())
                .description(task.getDescription())
                .assigneeId(task.getAssigneeId().orElse(null))
                .dueDate(task.getDueDate())
                .build();
    }

    public Task toModel() {
        return Task.task(id, title, description, assigneeId, dueDate);
    }
}