package com.sarvika.demo.exception;

public class ClientServiceException extends RuntimeException {

    public ClientServiceException(String message) {
        super(message);
    }

    public ClientServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

