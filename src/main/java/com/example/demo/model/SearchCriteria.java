package com.example.demo.model;

import lombok.Data;

@Data
public class SearchCriteria {
    private final String key;
    private final String operation;
    private final Object value;
}
