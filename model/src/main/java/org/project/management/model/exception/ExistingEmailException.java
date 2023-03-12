package org.project.management.model.exception;

public class ExistingEmailException extends BusinessException {
    public ExistingEmailException() {
    }

    public ExistingEmailException(String message) {
        super(message);
    }
}