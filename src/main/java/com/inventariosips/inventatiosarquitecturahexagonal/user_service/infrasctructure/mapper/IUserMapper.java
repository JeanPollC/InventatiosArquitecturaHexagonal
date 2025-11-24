package com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.mapper;

import com.inventariosips.inventatiosarquitecturahexagonal.user_service.domain.model.User;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.request.UserRequestDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.controller.dto.response.UserResponseDTO;
import com.inventariosips.inventatiosarquitecturahexagonal.user_service.infrasctructure.persistance.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserEntity UserToUserEntity (User user);
    User UserEntityToUser (UserEntity userEntity);

    User UserRequestDTOToUser (UserRequestDTO userRequestDTO);
    UserEntity UserRequestDTOToUserEntity (UserRequestDTO userRequestDTO);

    UserResponseDTO UserToUserResponseDTO(User user);

    //RESPONSE
    UserResponseDTO UserToUserResponseDTO(UserEntity userEntity);

    List<UserResponseDTO> lstUserToLstUserResponseDTO(List<User> ListUsers);
    List<User> lstUserEntityToLstUser(List<UserEntity> usersListEntity);



}
