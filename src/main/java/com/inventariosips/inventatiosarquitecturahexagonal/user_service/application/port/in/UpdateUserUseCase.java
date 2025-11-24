package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;

public interface UpdateUserUseCase {
    User updateUser(User user, Integer idUser);
}
