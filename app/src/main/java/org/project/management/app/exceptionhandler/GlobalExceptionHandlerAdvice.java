package org.project.management.app.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.project.management.app.rest.dto.ErrorDTO;
import org.project.management.model.exceptions.ExistingEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<ErrorDTO> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(ErrorDTO.builder()
                    .fieldName(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build());
        }
        return ResponseEntity.badRequest()
                .body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorDTO>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        List<ErrorDTO> violations = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            violations.add(ErrorDTO.builder()
                    .fieldName(violation.getPropertyPath().toString())
                    .message(violation.getMessage())
                    .build());
        }
        return ResponseEntity.badRequest()
                .body(violations);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(ErrorDTO.builder()
                        .message(ex.getMostSpecificCause().getMessage())
                        .build());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(ErrorDTO.builder()
                        .message(ex.getLocalizedMessage())
                        .build());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleInvalidFormatException(InvalidFormatException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ExistingEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDTO> handleExistingEmailException(ExistingEmailException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(409)
                .body(ErrorDTO.builder()
                        .message(ExceptionConstants.EXISTING_EMAIL)
                        .build());
    }
}
