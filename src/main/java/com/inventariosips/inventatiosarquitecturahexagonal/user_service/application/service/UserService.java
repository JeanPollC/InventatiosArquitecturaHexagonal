package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.service;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.CreateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.DeleteUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.GetUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.UpdateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out.UserRepositoryPort;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserErrorMessage;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserNotFoundException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase, UpdateUserUseCase, DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable, String filter) {
        return userRepositoryPort.getAllUsers(pageable, filter);
    }

    @Override
    public User getUserById(Long idUser) {
        return userRepositoryPort.getUserById(idUser)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessage.USER_DOES_NOT_EXISTS));
    }

    @Override
    public User createUser(User user) {

        if (user.name() == null){
            throw new UserException(UserErrorMessage.USER_NAME_NOT_NULL);
        }

        if (user.name().isEmpty()) {
            throw new UserException(UserErrorMessage.USER_NAME_NOT_EMPTY);
        }

        if (user.lastName() == null){
            throw new UserException(UserErrorMessage.USER_LASTNAME_NOT_NULL);
        }

        if (user.lastName().trim().isEmpty()){
            throw new UserException(UserErrorMessage.USER_LASTNAME_NOT_EMPTY);
        }

        if (!user.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new UserException(UserErrorMessage.USER_EMAIL_INVALID);
        }

        if (userRepositoryPort.existsByEmail(user.email())){
            throw new UserException(UserErrorMessage.USER_EMAIL_ALREADY_EXISTS);
        }

        if (user.userTypeId() == null){
            throw new UserException(UserErrorMessage.USERTYPE_NOT_NULL);
        }

        return userRepositoryPort.save(user);
    }

    @Override
    public User updateUser(UserUpdateDTO user, Long idUser) {
        User existingUser =  userRepositoryPort.getUserById(idUser)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessage.USER_DOES_NOT_EXISTS));

        user.name().ifPresent(name -> {
            if (name.trim().isEmpty()) {
                throw new UserException(UserErrorMessage.USER_NAME_NOT_EMPTY);
            }
        });

        user.lastName().ifPresent(lastName -> {
            if (lastName.trim().isEmpty()) {
                throw new UserException(UserErrorMessage.USER_LASTNAME_NOT_EMPTY);
            }
        });

        user.email().ifPresent(email -> {
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new UserException(UserErrorMessage.USER_EMAIL_INVALID);
            }

            if (!existingUser.email().equals(email) && userRepositoryPort.existsByEmail(email)){
                throw new UserException(UserErrorMessage.USER_EMAIL_ALREADY_EXISTS);
            }
        });

        user.status().ifPresent(status -> {
            if (status.trim().isEmpty()){
                throw new UserException(UserErrorMessage.USER_STATUS_NOT_EMPTY);
            }
        });

        User userUpdated = new User(
                existingUser.idUser(),
                user.name().orElse(existingUser.name()),
                user.lastName().orElse(existingUser.lastName()),
                user.email().orElse(existingUser.email()),
                existingUser.userTypeId(),
                user.status().orElse(existingUser.status())
        );

        return userRepositoryPort.updateUser(userUpdated);
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepositoryPort.getUserById(idUser)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessage.USER_DOES_NOT_EXISTS));

        userRepositoryPort.deleteUser(idUser);
    }

}
