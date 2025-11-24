package com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception;

public class UserErrorMessage {

    public static final String USER_NAME_NOT_NULL = "El nombre del usuario no puede ser nulo";
    public static final String USER_NAME_NOT_EMPTY = "El nombre del usuario no puede estar vacio";

    public static final String USER_LASTNAME_NOT_NULL = "El apellido del usuario no puede ser nulo";
    public static final String USER_LASTNAME_NOT_EMPTY = "El apellido del usuario no puede estar vacio";

    public static final String USER_EMAIL_INVALID = "El formato de email es invalido";
    public static final String USER_EMAIL_ALREADY_EXISTS = "El email ya existe";

    public static final String USERTYPE_NOT_NULL = "El tipo de usuario no puede venir nulo";

    private  UserErrorMessage(){}
}
