package com.blackGram.exception;

import org.springframework.http.HttpStatus;

public class BlackGramAPIException extends RuntimeException {

    private HttpStatus status;

    private String message;

    public BlackGramAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BlackGramAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
