package org.project.management.model.model;

import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Employee {
    private Long id;
    private String fullName;
    private String email;
    private Long phoneNumber;
    private LocalDate dateOfBirth;
    private BigDecimal monthlySalary;

    private Employee() {
    }

    private Employee(Long id,
                     String fullName,
                     String email,
                     Long phoneNumber,
                     LocalDate dateOfBirth,
                     BigDecimal monthlySalary) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.monthlySalary = monthlySalary;
    }

    public static Employee employee(Long id,
                                    String fullName,
                                    String email,
                                    Long phoneNumber,
                                    LocalDate dateOfBirth,
                                    BigDecimal monthlySalary) {
        return new Employee(id, fullName, email, phoneNumber, dateOfBirth, monthlySalary);
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    public Employee updatePersonalInfo(Employee employee) {
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.dateOfBirth = employee.getDateOfBirth();
        this.monthlySalary = employee.getMonthlySalary().orElse(null);

        MessagingBroker.produceEvent(Events.INFO_UPDATED);
        return employee;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Optional<BigDecimal> getMonthlySalary() {

        return Optional.ofNullable(this.monthlySalary);
    }
}