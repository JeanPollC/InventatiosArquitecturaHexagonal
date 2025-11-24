package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @NotNull
    private Long UserTypeId;

    @NotNull
    private String status;
}
