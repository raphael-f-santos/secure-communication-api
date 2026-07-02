package br.com.raphaelsantos.auth_service.dto;

public record LoginRequestDTO(
    String username,
    String password
) { } 
