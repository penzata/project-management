package org.project.management.model.model;

import org.project.management.model.service.impl.MessagingService;

import java.time.LocalDateTime;
import java.util.Optional;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Long assigneeId;
    private LocalDateTime dueDate;

    private Task() {
    }

    private Task(Long id,
                 String title,
                 String description,
                 Long assigneeId,
                 LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assigneeId = assigneeId;
        this.dueDate = dueDate;
    }

    public static Task task(Long id,
                            String title,
                            String description,
                            Long assigneeId,
                            LocalDateTime dueDate) {
        return new Task(id, title, description, assigneeId, dueDate);
    }

    public void assignTo(Employee employee) {
        this.assigneeId = employee.getId()
                .orElseThrow();

        //todo fix messaging service
        MessagingService.getInstance().produceEvent("Employee assigned to task");
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    public Optional<Long> getAssigneeId() {

        return Optional.ofNullable(this.assigneeId);
    }

    public Task updateParameters(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate().orElse(null);

        return task;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Optional<LocalDateTime> getDueDate() {

        return Optional.ofNullable(this.dueDate);
    }
}