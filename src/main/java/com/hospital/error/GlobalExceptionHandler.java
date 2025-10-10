package com.hospital.error;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UsernameNotFoundException ex){
        ApiError apiError = new ApiError("User not found with Username: "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex){
        ApiError apiError = new ApiError("Authentication Failed "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex){
        ApiError apiError = new ApiError("Invalid JWT Token "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){
        ApiError apiError = new ApiError("Access denied: Insufficient permission"+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex){
        ApiError apiError = new ApiError("An Unexpected Error Occurred "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
}
