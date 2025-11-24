package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.service;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.CreateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.GetUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out.UserRepositoryPort;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserErrorMessage;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserNotFoundException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
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

        if (user.lastName().isEmpty()){
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

/*
    @Override
    public User updateUser(User user, Long id) {
        userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return userRepo.save(userEntity);
    }


    @Override
    public Page<UserEntity> findAllUser(Pageable pageable, String filter) {
        if (filter != null && !filter.trim().isEmpty()) {
            return userRepo.findByGlobalFilter(filter.toLowerCase(), pageable);
        }
        return userRepo.findAll(pageable);
    }


    @Override
    public void deleteUser(Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        userRepo.deleteById(id);
    }*/

}
