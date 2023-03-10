package org.project.management.app.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.project.management.app.persistence.entity.EmployeeEntity;
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
                          BigDecimal monthlySalary) {

    public static EmployeeDTO fromEntity(EmployeeEntity employeeEntity) {
        return EmployeeDTO.builder()
                .id(employeeEntity.getId())
                .fullName(employeeEntity.getFullName())
                .email(employeeEntity.getEmail())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .dateOfBirth(employeeEntity.getDateOfBirth())
                .monthlySalary(employeeEntity.getMonthlySalary())
                .build();
    }

    public EmployeeEntity toEntity() {
        return EmployeeEntity.builder()
                .fullName(fullName)
                .email(email)
                .phoneNumber(phoneNumber)
                .dateOfBirth(dateOfBirth)
                .monthlySalary(monthlySalary)
                .build();
    }
}