package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
}
