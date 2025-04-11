package com.sarvika.demo.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String path;

    public ErrorResponse(String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.path = path;
    }
}

