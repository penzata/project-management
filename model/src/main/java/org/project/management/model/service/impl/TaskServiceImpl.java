package org.project.management.model.service.impl;

import org.project.management.model.exception.CompletedDateBeforeDueDateException;
import org.project.management.model.exception.ExpiredDueDateException;
import org.project.management.model.model.Employee;
import org.project.management.model.model.Task;
import org.project.management.model.repository.TaskRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.ProjectService;
import org.project.management.model.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(EmployeeService employeeService, ProjectService projectService, TaskRepository taskRepository) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        correctDateTimeCheck(task);
        projectService.getProjectOrThrow(task.getProjectId());
        return taskRepository.save(task);
    }

    private static void correctDateTimeCheck(Task task) {
        Optional<LocalDateTime> dueDate = task.getDueDate();
        Optional<LocalDateTime> completedDate = task.getCompletedDate();
        if (dueDate.isPresent() && dueDate.get().isBefore(LocalDateTime.now())) {
            throw new ExpiredDueDateException(dueDate.get().toString());
        }
        if (dueDate.isPresent() && completedDate.isPresent() && completedDate.get().isBefore(dueDate.get())) {
            throw new CompletedDateBeforeDueDateException(completedDate.get() + " : " + dueDate.get());
        }
    }

    @Override
    public Task getTask(Long id) {

        return taskRepository.findById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id);
        correctDateTimeCheck(task);
        Task updatedTask = taskToUpdate.updateParameters(task);

        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignEmployee(Long taskId, Long employeeId) {
        Task task = taskRepository.findById(taskId);
        Employee employee = employeeService.getEmployee(employeeId);
        task.assignTo(employee);

        return taskRepository.save(task);
    }

    @Override
    public void unassignAllFromEmployee(Long employeeId) {
        taskRepository.findAllByAssigneeId(employeeId)
                .forEach(task -> {
                    task.unassignFromEmployee();
                    taskRepository.save(task);
                });
    }

    @Override
    public Task unassignEmployee(Long id) {
        Task task = taskRepository.findById(id);
        task.unassignFromEmployee();
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    @Override
    public void deleteByProjectId(Long projectId) {
        taskRepository.deleteByProjectId(projectId);
    }

    @Override
    public List<Task> getAllOverdueTasks() {
        return taskRepository.findAllOverdueTasks();
    }
}