package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);
    boolean existsByEmail(String email);
}
