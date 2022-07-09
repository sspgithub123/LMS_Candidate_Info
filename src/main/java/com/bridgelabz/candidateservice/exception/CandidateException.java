package com.bridgelabz.candidateservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

public @Data class CandidateException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public CandidateException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public CandidateException(String message) {
        super(message);
    }
}
