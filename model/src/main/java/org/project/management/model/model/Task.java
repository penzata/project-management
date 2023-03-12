package org.project.management.model.model;

import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class Task {
    private final Logger log = LoggerFactory.getLogger(Task.class);
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

        MessagingBroker.produceEvent(Events.EMPLOYEE_ASSIGNED);
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

        MessagingBroker.produceEvent(Events.PARAMS_UPDATED);
        return this;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assigneeId=" + assigneeId +
                ", dueDate=" + dueDate +
                '}';
    }
}