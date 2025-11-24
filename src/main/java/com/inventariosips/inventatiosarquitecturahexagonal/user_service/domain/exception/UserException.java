package com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }
}
