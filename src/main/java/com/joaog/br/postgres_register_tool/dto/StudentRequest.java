package com.joaog.br.postgres_register_tool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotBlank(message = "Student Name is a required field!")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
        String name,

        @NotBlank(message = "Student E-mail is a required field!")
        @Email(message = "Not a valid E-mail was passed!")
        String email,

        @NotBlank(message = "Student Course is a required field!")
        @Size(min = 2, max = 100, message = "Course must be between 2 and 100 characters long")
        String course,

        @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
        String gender
) {
}
