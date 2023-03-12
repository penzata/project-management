package org.project.management.model.exception;

public class TaskAlreadyAssignedException extends BusinessException {
    public TaskAlreadyAssignedException() {
    }

    public TaskAlreadyAssignedException(String message) {
        super(message);
    }
}