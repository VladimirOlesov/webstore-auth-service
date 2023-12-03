package com.example.webstoreauthservice.service;

import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  User getUserByUsername(String username);

  UserDtoRegister save(UserDtoRegister userDto);

  UserDetailsService userDetailsService();

  UserDto getUserDtoByUsername(String username);
}