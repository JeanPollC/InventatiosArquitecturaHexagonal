package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    List<User> getAllUsers();
    Optional<User> getUserById(Long idUser);
    User save(User user);
    User updateUser(User user);
    boolean existsByEmail(String email);
}
