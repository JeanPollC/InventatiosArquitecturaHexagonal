package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.CreateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.DeleteUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.GetUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.UpdateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request.UserRequestDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request.UserUpdateDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response.UserResponseDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.mapper.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final IUserMapper mapperUser;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> lst = mapperUser.lstUserToLstUserResponseDTO(
                getUserUseCase.getAllUsers());
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<UserResponseDTO>> findAllUsersPageable(
            Pageable pageable,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter){
        Page<User> users = getUserUseCase.findAllUsers(pageable, filter);

        List<UserResponseDTO> dtoList = users.stream()
                .map(mapperUser::UserToUserResponseDTO)
                .toList();

        Page<UserResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                users.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long idUser){
        UserResponseDTO responseDTO = mapperUser.UserToUserResponseDTO(
            getUserUseCase.getUserById(idUser));

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userDTO) {
        User domainUser = mapperUser.UserRequestDTOToUser(userDTO);
        User savedUser = createUserUseCase.createUser(domainUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapperUser.UserToUserResponseDTO(savedUser));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable("id") Long id) {
        User user = updateUserUseCase.updateUser(userDTO, id);
        return ResponseEntity.ok(mapperUser.UserToUserResponseDTO(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long idUser) {
        deleteUserUseCase.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }
}
