package org.project.management.model.service.impl;

import org.project.management.model.exception.IdNotFoundException;
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

        return foundById(id);
    }

    private Task foundById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id.toString()));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = foundById(id);
        Task updatedTask = taskToUpdate.updateParameters(task);

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

    @Override
    public void unassignEmployee(Long employeeId) {
        List<Task> allByAssigneeId = taskRepository.findAllByAssigneeId(employeeId);

        allByAssigneeId.stream()
                .map(Task::unassignEmployee)
                .map(taskRepository::save)
                .close();
    }
}