package org.project.management.model.exception;

public class AlreadyAssignedEmployeeException extends BusinessException {
    public AlreadyAssignedEmployeeException() {
    }

    public AlreadyAssignedEmployeeException(String message) {
        super(message);
    }
}