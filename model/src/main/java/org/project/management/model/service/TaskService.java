package org.project.management.model.service;

import org.project.management.model.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTask(Long id);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task assignEmployee(Long taskId, Long employeeId);

    void unassignAllFromEmployee(Long employeeId);

    Task unassignEmployee(Long id);

    List<Task> getAllTasks();

    void deleteByProjectId(Long projectId);

    List<Task> getAllOverdueTasks();
}