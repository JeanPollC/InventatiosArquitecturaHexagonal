package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.out.UserRepositoryPort;
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
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
