package org.project.management.app.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.project.management.app.rest.dto.CustomMessageDTO;
import org.project.management.app.rest.dto.ViolationDTO;
import org.project.management.model.exception.BusinessException;
import org.project.management.model.exception.ExistingEmailException;
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
    public ResponseEntity<List<ViolationDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<ViolationDTO> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(ViolationDTO.builder()
                    .fieldName(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build());
        }
        return ResponseEntity.badRequest()
                .body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ViolationDTO>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        List<ViolationDTO> violations = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            violations.add(ViolationDTO.builder()
                    .fieldName(violation.getPropertyPath().toString())
                    .message(violation.getMessage())
                    .build());
        }
        return ResponseEntity.badRequest()
                .body(violations);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomMessageDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(CustomMessageDTO.builder()
                        .message(ex.getMostSpecificCause().getMessage())
                        .build());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomMessageDTO> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(CustomMessageDTO.builder()
                        .message(ex.getLocalizedMessage())
                        .build());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomMessageDTO> handleInvalidFormatException(InvalidFormatException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(CustomMessageDTO.builder()
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ExistingEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CustomMessageDTO> handleExistingEmailException(ExistingEmailException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(409)
                .body(CustomMessageDTO.builder()
                        .message(Messages.EXISTING_EMAIL)
                        .build());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<CustomMessageDTO> badRequestHandler(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(CustomMessageDTO.builder()
                        .message(ex.getClass().getSimpleName() + " - " + ex.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomMessageDTO> handleErrors(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(CustomMessageDTO.builder()
                        .message(ex.getMessage())
                        .build());
    }
}