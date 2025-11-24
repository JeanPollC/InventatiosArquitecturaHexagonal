package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.CreateUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.application.port.in.GetUserUseCase;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request.UserRequestDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response.UserResponseDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.mapper.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final IUserMapper mapperUser;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> lst = mapperUser.lstUserToLstUserResponseDTO(
                getUserUseCase.getAllUsers());
        return ResponseEntity.ok(lst);
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
    /*



    @GetMapping("/pageable")
    public ResponseEntity<Page<UserResponseDTO>> findAllUsersPageable(
            Pageable pageable,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter) throws Exception {
        // 1. Obtener la página de entidades del servicio
        Page<UserEntity> userPage = userService.findAllUser(pageable, filter);

        // 2. Convertir la lista de entidades (content) a DTOs
        List<UserResponseDTO> dtoList = userPage.getContent().stream()
                .map(mapperUser::UserEntityToUserResponseDTO)
                .collect(Collectors.toList());

        // 3. Reconstruir la respuesta Page usando los metadatos de la página original
        Page<UserResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                userPage.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findByIdUser(@PathVariable("id") Integer id) throws Exception {
        UserResponseDTO dto = mapperUser.UserEntityToUserResponseDTO(userService.findByIdUser(id));

        return ResponseEntity.ok(dto);
    }*/


    /*@PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserRequestDTO userDTO, @PathVariable("id") Integer id) throws Exception {
        userDTO.setIdUser(id);
        UserEntity userEntity = userService.updateUser(mapperUser.userDTOToUserEntity(userDTO), id);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) throws Exception {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }*/

}
