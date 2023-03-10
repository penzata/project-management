package org.project.management.model.repository;

import org.project.management.model.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(Long id);

    void deleteById(Long id);

    List<Long> getTopFiveEmployeeIds();
}