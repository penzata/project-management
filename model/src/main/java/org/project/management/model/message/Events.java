package org.project.management.model.message;

public class Events {
    public static final String EMPLOYEE_ASSIGNED_TASK = "Employee assigned to task.";
    public static final String EMPLOYEE_ASSIGNED_PROJECT = "Employee assigned to project.";
    public static final String EMPLOYEE_UNASSIGNED_TASK = "Employee unassigned from task.";
    public static final String EMPLOYEE_UNASSIGNED_PROJECT = "Employee unassigned from project.";
    public static final String TASK_UPDATED = "Successfully updated task's parameters.";
    public static final String EMPLOYEE_UPDATED = "Successfully updated employee's personal info.";
    public static final String PROJECT_UPDATED = "Successfully updated project's info.";

    private Events() {
    }
}