package com.sarvika.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(
            OrderNotFoundException ex, WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse error = new ErrorResponse(ex.getMessage(), path);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientServiceException.class)
    public ResponseEntity<ErrorResponse> handleClientServiceException(
            ClientServiceException ex, WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse error = new ErrorResponse("Client service failure: " + ex.getMessage(), path);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(
            Exception ex, WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse error = new ErrorResponse("Internal Server Error", path);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
