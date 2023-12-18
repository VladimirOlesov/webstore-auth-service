package com.example.webstoreauthservice.service;

import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  User getUserByUsername(String username);

  UserDtoRegister save(UserDtoRegister userDto);

  UserDetailsService userDetailsService();

  UserDto getUserDtoByUsername(String username);

  UserDto getUserDtoByUuid(UUID uuid);
}