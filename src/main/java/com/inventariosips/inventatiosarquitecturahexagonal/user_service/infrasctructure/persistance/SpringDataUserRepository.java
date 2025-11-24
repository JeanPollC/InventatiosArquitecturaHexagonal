package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE LOWER(u.name) LIKE %:filter% OR " +
            "LOWER(u.lastName) LIKE %:filter% OR " +
            "LOWER(u.email) LIKE %:filter% OR " +
            "LOWER(u.status) LIKE %:filter%")
    Page<UserEntity> findByGlobalFilter( Pageable pageable, @Param("filter") String filter);
}
