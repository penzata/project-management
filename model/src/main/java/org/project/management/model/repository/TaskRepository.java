package org.project.management.model.repository;

import org.project.management.model.model.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Task task);

    Task findById(Long id);

    void deleteById(Long id);

    List<Task> findAllByAssigneeId(Long id);

    List<Task> findAllTasks();

    void deleteByProjectId(Long projectId);
}