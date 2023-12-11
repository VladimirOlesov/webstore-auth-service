package com.example.webstoreauthservice.service.impl;

import com.example.commoncode.exception.DuplicateException;
import com.example.webstoreauthservice.model.dto.UserDtoLogin;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import com.example.webstoreauthservice.repository.AuthService;
import com.example.webstoreauthservice.repository.JwtService;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserService userService;

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDtoRegister register(UserDtoRegister userDto) {

    if (userRepository.existsByUsername(
        userDto.username())
        || userRepository.existsByEmail(userDto.email())) {
      throw new DuplicateException(
          "Пользователь c таким именем или электронной почтой уже существует");
    }
    return userService.save(userDto);
  }

  @Override
  public String authenticate(UserDtoLogin userDtoLogin) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDtoLogin.username(),
            userDtoLogin.password()));

    User user = userService.getUserByUsername(userDtoLogin.username());
    return jwtService.generateToken(user);
  }
}