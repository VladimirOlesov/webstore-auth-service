package com.example.webstoreauthservice.repository;

import com.example.webstoreauthservice.model.dto.UserDtoLogin;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;

public interface AuthService {

  UserDtoRegister register(UserDtoRegister userDto);

  String authenticate(UserDtoLogin userDto);

}