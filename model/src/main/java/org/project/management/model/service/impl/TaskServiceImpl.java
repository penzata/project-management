package org.project.management.model.service.impl;

import org.project.management.model.exception.IdNotFoundException;
import org.project.management.model.model.Employee;
import org.project.management.model.model.Task;
import org.project.management.model.repository.TaskRepository;
import org.project.management.model.service.EmployeeService;
import org.project.management.model.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
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

        return foundById(id);
    }

    private Task foundById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        log.info("=========task: {}", task);
        Task taskToUpdate = foundById(id);
        log.info("=========taskToUpdate: {}", taskToUpdate);
        Task updatedTask = taskToUpdate.updateParameters(task);
log.info("=========updatedTask: {}", updatedTask);
        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        foundById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignEmployee(Long taskId, Long assigneeId) {
        Task task = foundById(taskId);
        Employee employee = employeeService.getEmployee(assigneeId);
        task.assignTo(employee);

        return taskRepository.save(task);
    }

    @Override
    public List<Long> getTopFiveEmployeeIdsInPastMonth() {
        return taskRepository.findTopFiveEmployeeIdsInPastMonth();
    }
}