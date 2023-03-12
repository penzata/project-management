package org.project.management.model.exception;

public class IdNotFoundException extends BusinessException {
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}