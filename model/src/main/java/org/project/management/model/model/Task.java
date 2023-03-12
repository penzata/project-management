package org.project.management.model.model;

import org.project.management.model.exception.TaskAlreadyAssignedException;
import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Task {
    Logger log = LoggerFactory.getLogger(Task.class);
    private Long id;
    private String title;
    private String description;
    private Long assigneeId;
    private LocalDateTime dueDate;

    private LocalDateTime completedDate;

    private Task() {
    }

    private Task(Long id,
                 String title,
                 String description,
                 Long assigneeId,
                 LocalDateTime dueDate,
                 LocalDateTime completedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assigneeId = assigneeId;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
    }

    public static Task task(Long id,
                            String title,
                            String description,
                            Long assigneeId,
                            LocalDateTime dueDate,
                            LocalDateTime completedDate) {
        return new Task(id, title, description, assigneeId, dueDate, completedDate);
    }

    public void assignTo(Employee employee) {
        if(this.assigneeId != null) {
            throw new TaskAlreadyAssignedException(employee.toString());
        }
        this.assigneeId = employee.getId();

        MessagingBroker.produceEvent(Events.EMPLOYEE_ASSIGNED);
    }

    public void unassignEmployee() {
        this.assigneeId = null;
        MessagingBroker.produceEvent(Events.EMPLOYEE_UNASSIGNED);
    }

    public Long getId() {
        return this.id;
    }

    public Optional<Long> getAssigneeId() {

        return Optional.ofNullable(this.assigneeId);
    }

    public Task updateParameters(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();

        this.dueDate = task.getDueDate().orElse(null);
        this.completedDate = task.getCompletedDate().orElse(null);

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

    public Optional<LocalDateTime> getCompletedDate() {
        return Optional.ofNullable(completedDate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                title.equals(task.title) &&
                description.equals(task.description) &&
                Objects.equals(assigneeId, task.assigneeId) &&
                dueDate.equals(task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, dueDate);
    }
}