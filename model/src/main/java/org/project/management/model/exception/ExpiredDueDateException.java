package org.project.management.model.exception;

public class ExpiredDueDateException extends BusinessException {
    public ExpiredDueDateException() {
    }

    public ExpiredDueDateException(String message) {
        super(message);
    }
}