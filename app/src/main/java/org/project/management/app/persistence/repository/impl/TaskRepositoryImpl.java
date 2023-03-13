package org.project.management.app.persistence.repository.impl;

import lombok.AllArgsConstructor;
import org.project.management.app.persistence.entity.TaskEntity;
import org.project.management.app.persistence.repository.TaskRepositoryJpa;
import org.project.management.model.exception.IdNotFoundException;
import org.project.management.model.model.Task;
import org.project.management.model.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskRepositoryJpa taskRepositoryJpa;

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = TaskEntity.fromModel(task);
        TaskEntity savedTask = taskRepositoryJpa.save(taskEntity);
        return savedTask.toModel();
    }

    @Override
    public Task findById(Long id) {
        TaskEntity taskEntity = taskRepositoryJpa.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));

        return taskEntity.toModel();
    }

    @Override
    public void deleteById(Long id) {
        taskRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Task> findAllByAssigneeId(Long id) {
        List<TaskEntity> allByAssigneeId = taskRepositoryJpa.findAllByAssigneeId(id);

        return allByAssigneeId.stream()
                .map(TaskEntity::toModel)
                .toList();
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepositoryJpa.findAll().stream()
                .map(TaskEntity::toModel)
                .toList();
    }

    @Override
    public void deleteByProjectId(Long projectId) {
        taskRepositoryJpa.deleteByProjectId(projectId);
    }

    @Override
    public List<Task> findAllOverdueTasks() {
        return taskRepositoryJpa.findAllByCompletedDateAfterDueDate().stream()
                .map(TaskEntity::toModel)
                .toList();
    }
}