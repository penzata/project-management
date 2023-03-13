package org.project.management.app.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.project.management.app.exceptionhandler.Messages;
import org.project.management.app.rest.dto.CustomMessageDTO;
import org.project.management.app.rest.dto.TaskDTO;
import org.project.management.model.model.Task;
import org.project.management.model.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping(params = {"employeeId", "projectId"})
    public List<TaskDTO> getAllTasks(@RequestParam(required = false, name = "employeeId") String employeeId,
                                     @RequestParam(required = false, name = "projectId") String projectId) {
        return taskService.getAllTasks().stream()
                .map(TaskDTO::fromModel)
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO.toModel());
        return TaskDTO.fromModel(task);
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@Min(1) @PathVariable Long id) {
        Task task = taskService.getTask(id);
        return TaskDTO.fromModel(task);
    }

    @DeleteMapping("/{id}")
    public CustomMessageDTO deleteTask(@Min(1) @PathVariable Long id) {
        taskService.deleteTask(id);
        return CustomMessageDTO.builder()
                .message(Messages.DELETED_TASK)
                .build();
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@Min(1) @PathVariable Long id,
                              @Valid @RequestBody TaskDTO taskDTO) {
        Task task = taskService.updateTask(id, taskDTO.toModel());
        return TaskDTO.fromModel(task);
    }

    @PutMapping(value = "/{id}/assignee/{employeeId}")
    public TaskDTO assignEmployee(@Min(1) @PathVariable Long id,
                                  @PathVariable(name = "employeeId") Long employeeId) {
        Task task = taskService.assignEmployee(id, employeeId);
        return TaskDTO.fromModel(task);
    }

    @DeleteMapping(value = "/{id}/assignee")
    public TaskDTO unassignEmployee(@Min(1) @PathVariable Long id) {
        Task task = taskService.unassignEmployee(id);
        return TaskDTO.fromModel(task);
    }
}