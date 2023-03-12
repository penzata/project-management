package org.project.management.app.persistence.repository.impl;

import lombok.AllArgsConstructor;
import org.project.management.app.persistence.entity.TaskEntity;
import org.project.management.app.persistence.repository.TaskRepositoryJpa;
import org.project.management.model.exception.IdNotFoundException;
import org.project.management.model.model.Task;
import org.project.management.model.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task> findById(Long id) {
        TaskEntity taskEntity = taskRepositoryJpa.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));

        return Optional.ofNullable(taskEntity.toModel());
    }

    @Override
    public void deleteById(Long id) {
        taskRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Long> findTopFiveEmployeeIdsInPastMonth() {

//        return taskRepositoryJpa.findTopFiveEmployeeIdsInPastMonth();
        return null;
    }
}
