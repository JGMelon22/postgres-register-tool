package com.joaog.br.postgres_register_tool.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotEmpty @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long") String name,
        @NotEmpty @Size(min = 2, max = 100, message = "Course must be between 2 and 100 characters long") String course,
        @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other") @NotEmpty @Size(min = 4, max = 6, message = "Gender type must be between 4 and 6 characters long") String gender
        ) {
}
