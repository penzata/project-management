package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.project.management.model.model.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record EmployeeDTO(@JsonProperty(access = JsonProperty.Access.READ_ONLY)
                          Long id,
                          @NotBlank
                          String fullName,
                          @NotBlank
                          @Email
                          String email,
                          @NotNull
                          Long phoneNumber,
                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                          LocalDate dateOfBirth,
                          @NotNull
                          BigDecimal monthlySalary,
                          @JsonProperty(access = JsonProperty.Access.READ_ONLY)
                          Long projectId) {

    public static EmployeeDTO fromModel(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .dateOfBirth(employee.getDateOfBirth())
                .monthlySalary(employee.getMonthlySalary())
                .projectId(employee.getProjectId())
                .build();
    }

    public Employee toModel() {
        return Employee.employee(id, fullName, email, phoneNumber, dateOfBirth, monthlySalary, projectId);
    }
}