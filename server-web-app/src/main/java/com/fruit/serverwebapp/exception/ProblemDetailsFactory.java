package com.fruit.serverwebapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.Instant;

public final class ProblemDetailsFactory {

    private ProblemDetailsFactory() {}

    public static ProblemDetail of(HttpStatus status, String message, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, message);
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }
}