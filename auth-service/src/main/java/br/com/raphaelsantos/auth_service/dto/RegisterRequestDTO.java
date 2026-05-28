package br.com.raphaelsantos.auth_service.dto;

public record RegisterRequestDTO (
    String username,
    String password
) { }
