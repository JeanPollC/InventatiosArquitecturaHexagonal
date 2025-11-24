package com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model;

public record User(
        Long id,
        String name,
        String lastName,
        String email,
        Long userTypeId,
        String status
) {
}
