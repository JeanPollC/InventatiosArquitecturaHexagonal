package com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends UserException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
