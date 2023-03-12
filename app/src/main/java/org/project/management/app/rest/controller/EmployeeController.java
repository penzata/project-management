package org.project.management.app.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.project.management.app.exceptionhandler.Messages;
import org.project.management.app.rest.dto.CustomMessageDTO;
import org.project.management.app.rest.dto.EmployeeDTO;
import org.project.management.model.model.Employee;
import org.project.management.model.aggregators.EmployeeWithCompletedTasks;
import org.project.management.model.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(EmployeeDTO::fromModel)
                .toList();
    }

    @GetMapping("/top/{maxNum}")
    public List<EmployeeWithCompletedTasks> getTopEmployees(@PathVariable String maxNum) {
        return employeeService.getTopEmployees(maxNum);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO.toModel());

        return EmployeeDTO.fromModel(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@Min(1) @PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);

        return EmployeeDTO.fromModel(employee);
    }

    @DeleteMapping("/{id}")
    public CustomMessageDTO deleteEmployee(@Min(1) @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return CustomMessageDTO.builder()
                .message(Messages.DELETED_EMPLOYEE)
                .build();
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@Min(1) @PathVariable Long id,
                                      @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.updateEmployee(id, employeeDTO.toModel());
        return EmployeeDTO.fromModel(employee);
    }

    @PutMapping(value = "/{id}/project/{projectId}")
    public EmployeeDTO assignToProject(@Min(1) @PathVariable Long id,
                                  @PathVariable(name = "projectId") Long projectId) {
        Employee employee = employeeService.assignToProject(id, projectId);
        return EmployeeDTO.fromModel(employee);
    }

    @DeleteMapping(value = "/{id}/project")
    public EmployeeDTO unassignFromProject(@Min(1) @PathVariable Long id) {
        Employee employee = employeeService.unassignFromProject(id);
        return EmployeeDTO.fromModel(employee);
    }
}