package com.example.webstoreauthservice.model.mapper;

import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {

  @Mapping(source = "password", target = "password", qualifiedBy = CustomMapping.class)
  User userDtoToUser(UserDtoRegister userDto);

  @Mapping(target = "password", constant = "")
  UserDtoRegister convertToDto(User user);
  @Mapping(source = "id", target = "userId")
  UserDto toUserDto(User user);
}