package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        
        @NotNull(message = "El nombre del usuario no puede ser nulo")
        @NotEmpty(message = "El nombre no puede estar vacio")
        String name,

        @NotNull(message = "El apellido del usuario no puede ser nulo")
        @NotEmpty(message = "El apellido no puede estar vacio")
        String lastName,

        @NotBlank(message = "El email del usuario no puede estar vacío")
        @Email(message = "El email registrado debe tener un formato válido: @mail.com")
        String email,

        @NotNull(message = "El tipo de usuario no puede venir nulo")
        Long userTypeId,

        @NotNull(message = "El estado no puede venir nulo")
        @NotEmpty(message = "El estado no puede estar vacio")
        String status
) {}

