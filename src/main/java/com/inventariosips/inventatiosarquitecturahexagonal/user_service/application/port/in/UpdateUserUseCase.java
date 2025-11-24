package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance.UserEntity;

public interface UpdateUserUseCase {
    User updateUser(User user, Long idUser);
}