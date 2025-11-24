package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);

}
