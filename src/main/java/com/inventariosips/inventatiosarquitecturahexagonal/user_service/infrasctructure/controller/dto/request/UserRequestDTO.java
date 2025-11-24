package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(

        @NotBlank(message = "El nombre del usuario no puede estar vacío")
        String name,

        @NotBlank(message = "El apellido del usuario no puede estar vacío")
        String lastName,

        @NotBlank(message = "El email del usuario no puede estar vacío")
        @Email(message = "El email registrado debe tener un formato válido: @mail.com")
        String email,

        @NotNull(message = "El tipo de usuario no puede venir nulo")
        Long userTypeId,

        @NotBlank(message = "El estado del usuario no puede estar vacío")
        String status
) {}

