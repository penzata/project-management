package org.project.management.model.service.impl;

import org.project.management.model.model.Employee;
import org.project.management.model.model.Task;
import org.project.management.model.repository.TaskRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final EmployeeService employeeService;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(EmployeeService employeeService, TaskRepository taskRepository) {
        this.employeeService = employeeService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public Task getTask(Long id) {

        return findById(id);
    }

    private Task findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = findById(id);
        Task updatedTask = taskToUpdate.updateParameters(task);

        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        findById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignEmployee(Long taskId, Long employeeId) {
        Task task = findById(taskId);
        Employee employee = employeeService.getEmployee(employeeId);
        task.assignTo(employee);

        return taskRepository.save(task);
    }

    @Override
    public void unassignDeletedEmployee(Long employeeId) {
        List<Task> allByAssigneeId = taskRepository.findAllByAssigneeId(employeeId);
        allByAssigneeId
                .forEach(task -> {
                    task.unassignEmployee();
                    taskRepository.save(task);
                });
    }

    @Override
    public Task unassignEmployee(Long id) {
        Task task = taskRepository.findById(id);
        task.unassignEmployee();
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }
}