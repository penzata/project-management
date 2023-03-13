package org.project.management.model.aggregators;

import java.util.Objects;

public class EmployeeWithCompletedTasks {
    private Long id;
    private String fullName;
    private String email;
    private int completedTasks;

    public EmployeeWithCompletedTasks(Long id,
                                      String fullName,
                                      String email,
                                      int completedTasks) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.completedTasks = completedTasks;
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    @Override
    public String toString() {
        return "EmployeeWithCompletedTasks{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", completedTasks=" + completedTasks +
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
        EmployeeWithCompletedTasks employee = (EmployeeWithCompletedTasks) o;
        return Objects.equals(id, employee.id) &&
                fullName.equals(employee.fullName) &&
                email.equals(employee.email) &&
                completedTasks == employee.completedTasks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, completedTasks);
    }
}