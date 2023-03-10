package org.project.management.app.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.project.management.model.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees", indexes = {
        @Index(name = "email_idx", columnList = "email")
})
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    private String fullName;
    private String email;
    private Long phoneNumber;
    private LocalDate dateOfBirth;
    private BigDecimal monthlySalary;
    private Long projectId;

    public static EmployeeEntity fromModel(Employee employee) {
        return EmployeeEntity.builder()
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