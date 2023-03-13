package org.project.management.model.exception;

public class CompletedDateBeforeDueDateException extends BusinessException {
    public CompletedDateBeforeDueDateException() {
    }

    public CompletedDateBeforeDueDateException(String message) {
        super(message);
    }
}