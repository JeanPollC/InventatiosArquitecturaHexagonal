package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out.UserRepositoryPort;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserErrorMessage;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserNotFoundException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;
    private final IUserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.lstUserEntityToLstUser(repository.findAll());
    }

    @Override
    public Optional<User> getUserById(Long idUser) {
        return repository.findById(idUser)
                .map(userMapper::UserEntityToUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.UserToUserEntity(user);
        return userMapper.UserEntityToUser(repository.save(userEntity));
    }

    @Override
    public User updateUser(User user) {
        UserEntity userExisisting = repository.findById(user.idUser())
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessage.USER_DOES_NOT_EXISTS));

        userExisisting.setName(user.name());
        userExisisting.setLastName(user.lastName());
        userExisisting.setEmail(user.email());
        userExisisting.setStatus(user.status());

        UserEntity userUpdate = repository.save(userExisisting);

        return userMapper.UserEntityToUser(userUpdate);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
