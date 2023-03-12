package org.project.management.model.exceptions;

public class IdNotFoundException extends BusinessException {
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}