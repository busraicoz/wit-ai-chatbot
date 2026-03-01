package com.ing.wit.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChatRequest(
        @NotBlank(message = "Query cannot be blank")
        @Size(max = 1000, message = "Query is too long.")
        String message
) {}
