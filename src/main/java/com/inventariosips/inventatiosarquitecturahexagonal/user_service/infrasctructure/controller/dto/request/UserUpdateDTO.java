package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request;

import java.util.Optional;

public record UserUpdateDTO(
        Optional<String> name,
        Optional<String> lastName,
        Optional<String> email,
        Optional<String> status
) {}
