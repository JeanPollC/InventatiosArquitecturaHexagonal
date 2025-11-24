package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.exception;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.exception.UserNotFoundException;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList().toString();
        return new UserErrorResponse(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new UserErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public UserErrorResponse handleUserException(UserException ex) {
        return new UserErrorResponse(ex.getMessage());
    }


}
