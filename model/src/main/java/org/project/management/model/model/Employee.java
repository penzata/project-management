package org.project.management.model.model;

import org.project.management.model.message.Events;
import org.project.management.model.message.MessagingBroker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
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
        this.monthlySalary = employee.getMonthlySalary();

        MessagingBroker.produceEvent(Events.INFO_UPDATED);
        return this;
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

    public BigDecimal getMonthlySalary() {

        return monthlySalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", monthlySalary=" + monthlySalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                fullName.equals(employee.fullName) &&
                email.equals(employee.email) &&
                phoneNumber.equals(employee.phoneNumber) &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                monthlySalary.equals(employee.monthlySalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, phoneNumber, dateOfBirth, monthlySalary);
    }
}