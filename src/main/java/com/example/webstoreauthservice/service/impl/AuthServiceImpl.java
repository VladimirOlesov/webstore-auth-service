package com.example.webstoreauthservice.service.impl;

import com.example.commoncode.exception.DuplicateException;
import com.example.webstoreauthservice.model.dto.UserDtoLogin;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import com.example.webstoreauthservice.service.AuthService;
import com.example.webstoreauthservice.service.JwtService;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса {@link AuthService} для аутентификации и регистрации пользователей.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserService userService;

  private final UserRepository userRepository;

  /**
   * Регистрация нового пользователя на основе предоставленных данных.
   *
   * @param userDto Объект {@link UserDtoRegister} с данными для регистрации.
   * @return Объект {@link UserDtoRegister} зарегистрированного пользователя.
   * @throws DuplicateException, если пользователь с таким именем или электронной почтой уже
   *                             существует.
   */
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

  /**
   * Аутентификация пользователя на основе предоставленных данных.
   *
   * @param userDtoLogin Объект {@link UserDtoLogin} с данными для аутентификации.
   * @return Строка, представляющая сгенерированный токен для аутентифицированного пользователя.
   * @throws AuthenticationException, если аутентификация не прошла успешно.
   */
  @Override
  public String authenticate(UserDtoLogin userDtoLogin) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDtoLogin.username(),
            userDtoLogin.password()));

    User user = userService.getUserByUsername(userDtoLogin.username());
    String generatedToken = jwtService.generateToken(user);
    log.debug("Юзер c id = {} успешно прошел аутентификацию", user.getId());
    return generatedToken;
  }
}