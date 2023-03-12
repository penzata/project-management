package org.project.management.app.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.project.management.app.exceptionhandler.MessageConstants;
import org.project.management.app.persistence.entity.EmployeeEntity;
import org.project.management.app.rest.dto.CustomMessageDTO;
import org.project.management.app.rest.dto.EmployeeDTO;
import org.project.management.model.model.Employee;
import org.project.management.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO.toEntity().toModel());

        return EmployeeDTO.fromEntity(EmployeeEntity
                .fromModel(employee));
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@Min(1) @PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);

        return EmployeeDTO.fromEntity(EmployeeEntity
                .fromModel(employee));
    }

    @DeleteMapping("/{id}")
    public CustomMessageDTO deleteEmployee(@Min(1) @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return CustomMessageDTO.builder()
                .message(MessageConstants.DELETED_EMPLOYEE)
                .build();
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@Min(1) @PathVariable Long id,
                                      @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.updateEmployee(id, employeeDTO.toEntity().toModel());
        return EmployeeDTO.fromEntity(EmployeeEntity
                .fromModel(employee));
    }
}