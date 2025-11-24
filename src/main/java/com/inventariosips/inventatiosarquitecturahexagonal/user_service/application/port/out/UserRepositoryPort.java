package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    List<User> getAllUsers();
    Page<User> getAllUsers(Pageable pageable, String filter);
    Optional<User> getUserById(Long idUser);
    User save(User user);
    User updateUser(User user);
    void deleteUser(Long idUser);
    boolean existsByEmail(String email);
}
