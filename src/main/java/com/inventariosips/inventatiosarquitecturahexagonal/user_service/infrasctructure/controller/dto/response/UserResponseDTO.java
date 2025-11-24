package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response;

public record UserResponseDTO(
        String name,
        String lastName,
        String email,
        Long UserTypeId,
        String status
) { }
