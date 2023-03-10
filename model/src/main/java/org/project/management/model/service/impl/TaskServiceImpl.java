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

    // todo add custom exception and handler
    @Override
    public Task getTask(Long id) {

        return taskRepository.findById(id)
                .orElseThrow();
    }

    // todo add custom exception and handler
    @Override
    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow();

        Task updatedTask = taskToUpdate.updateParameters(task);

        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignEmployee(Long taskId, Long assigneeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow();
        Employee employee = employeeService.getEmployee(assigneeId);
        task.assignTo(employee);

        return taskRepository.save(task);
    }

    @Override
    public List<Long> getTopFiveEmployeeIdsInPastMonth() {
        return taskRepository.findTopFiveEmployeeIdsInPastMonth();
    }
}