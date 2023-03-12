package org.project.management.app.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.project.management.app.exceptionhandler.MessageConstants;
import org.project.management.app.persistence.entity.TaskEntity;
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

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO.toEntity().toModel());

        return TaskDTO.fromEntity(TaskEntity
                .fromModel(task));
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@Min(1) @PathVariable Long id) {
        Task task = taskService.getTask(id);

        return TaskDTO.fromEntity(TaskEntity
                .fromModel(task));
    }

    @DeleteMapping("/{id}")
    public CustomMessageDTO deleteTask(@Min(1) @PathVariable Long id) {
        taskService.deleteTask(id);
        return CustomMessageDTO.builder()
                .message(MessageConstants.DELETED_TASK)
                .build();
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@Min(1) @PathVariable Long id,
                              @Valid @RequestBody TaskDTO taskDTO) {
        Task task = taskService.updateTask(id, taskDTO.toEntity().toModel());
        return TaskDTO.fromEntity(TaskEntity
                .fromModel(task));
    }

    @PutMapping(value = "/{id}", params = "assigneeId")
    public TaskDTO assignEmployee(@Min(1) @PathVariable Long id,
                                  @RequestParam(name = "assigneeId") Long assigneeId) {
        Task task = taskService.assignEmployee(id, assigneeId);
        return TaskDTO.fromEntity(TaskEntity
                .fromModel(task));
    }
}