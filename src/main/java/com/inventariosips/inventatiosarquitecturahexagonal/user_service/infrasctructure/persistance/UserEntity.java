package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(length = 60, unique = true)
    private String email;

    @Column(name = "user_type_id")
    private Long userTypeId;

    @Column(length = 20)
    private String status;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        UserEntity that = (UserEntity) object;
        return Objects.equals(idUser, that.idUser) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, lastName, email, status);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
