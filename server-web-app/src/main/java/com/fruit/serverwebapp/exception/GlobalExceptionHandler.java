package com.fruit.serverwebapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(NotJoinedException.class)
    public ProblemDetail handleNotJoined(NotJoinedException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler({
            AlreadyExistsException.class,
            AlreadyJoinedException.class,
            SameStatusException.class
    })
    public ProblemDetail handleConflict(RuntimeException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(OrganizationDeactivatedException.class)
    public ProblemDetail handleDeactivated(OrganizationDeactivatedException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.FORBIDDEN, ex.getMessage(), request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        ProblemDetail pd = ProblemDetailsFactory.of(HttpStatus.BAD_REQUEST, "Validation failed", request);
        pd.setProperty("errors", errors);
        return pd;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingParam(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("{}", ex.getMessage());
        return ProblemDetailsFactory.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleOther(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error", ex);
        return ProblemDetailsFactory.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                request);
    }
}
