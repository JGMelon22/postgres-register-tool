package com.joaog.br.postgres_register_tool.dto;

public record StudentResponse(
        int id,
        String name,
        String email,
        String course,
        String gender) {
}
