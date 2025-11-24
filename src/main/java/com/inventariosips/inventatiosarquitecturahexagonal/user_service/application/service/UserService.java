package com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.service;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.CreateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out.UserRepositoryPort;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserErrorMessage;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserNotFoundException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {

        if (user.name() == null){
            throw new IllegalStateException(UserErrorMessage.USER_NAME_NOT_NULL);
        }

        if (user.name().isEmpty()) {
            throw new IllegalStateException(UserErrorMessage.USER_NAME_NOT_EMPTY);
        }

        if (user.lastName() == null){
            throw new IllegalStateException(UserErrorMessage.USER_LASTNAME_NOT_NULL);
        }

        if (user.lastName().isEmpty()){
            throw new IllegalStateException(UserErrorMessage.USER_LASTNAME_NOT_EMPTY);
        }

        if (!user.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new IllegalStateException(UserErrorMessage.USER_EMAIL_INVALID);
        }

        if (userRepositoryPort.existsByEmail(user.email())){
            throw new IllegalStateException(UserErrorMessage.USER_EMAIL_ALREADY_EXISTS);
        }

        if (user.userTypeId() == null){
            throw new IllegalStateException(UserErrorMessage.USERTYPE_NOT_NULL);
        }

        return userRepositoryPort.save(user);
    }

/*
    @Override
    public UserEntity updateUser(UserEntity userEntity, Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return userRepo.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Page<UserEntity> findAllUser(Pageable pageable, String filter) {
        if (filter != null && !filter.trim().isEmpty()) {
            return userRepo.findByGlobalFilter(filter.toLowerCase(), pageable);
        }
        return userRepo.findAll(pageable);
    }

    @Override
    public UserEntity findByIdUser(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        userRepo.deleteById(id);
    }*/

}
