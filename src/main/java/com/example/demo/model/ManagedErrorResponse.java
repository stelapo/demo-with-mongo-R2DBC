package com.example.demo.model;

import lombok.Data;

@Data
public class ManagedErrorResponse {
    private final int httpStatus;
    private final String error;
    private final String message;
}
