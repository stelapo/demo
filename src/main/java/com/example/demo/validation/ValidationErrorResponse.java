package com.example.demo.validation;

import com.example.demo.model.Violation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}
