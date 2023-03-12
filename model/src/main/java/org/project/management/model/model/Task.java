package org.project.management.model.model;

import org.project.management.model.exception.TaskAlreadyAssignedException;
import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;

import java.time.LocalDateTime;
import java.util.Objects;
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
        if(this.assigneeId != null) {
            throw new TaskAlreadyAssignedException(employee.toString());
        }
        Optional<Long> employeeId = employee.getId();
        employeeId.ifPresent(eId -> this.assigneeId = eId);

        MessagingBroker.produceEvent(Events.EMPLOYEE_ASSIGNED);
    }

    public Task unassignEmployee() {
        this.assigneeId = null;
        return this;
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