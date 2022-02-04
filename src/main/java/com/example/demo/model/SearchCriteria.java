package com.example.demo.model;

import lombok.Data;

@Data
public class SearchCriteria {
    public static final String searchStringPattern = "(\\w+?)(:|<|>)\"([^\"]+)\"";

    public static final String searchStringPatternForController = "(" + searchStringPattern + "[~]?)+?";

    private final String key;
    private final String operation;
    private final Object value;
}
