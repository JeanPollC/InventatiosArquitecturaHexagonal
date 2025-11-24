package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetUserUseCase {
    User getUserById(Long idUser);
    List<User> getAllUsers();
    Page<User> findAllUsers(Pageable pageable, String filter);
}
