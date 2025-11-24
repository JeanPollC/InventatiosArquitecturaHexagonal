package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance.UserEntity;

import java.util.List;

public interface GetUserUseCase {
    User getUserById(Long idUser);
    List<User> getAllUsers();
}
