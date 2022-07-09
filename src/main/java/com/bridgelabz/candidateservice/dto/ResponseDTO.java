package com.bridgelabz.candidateservice.dto;

import lombok.Data;

public @Data class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public ResponseDTO() {
    }

    public ResponseDTO(String s) {
    }
}