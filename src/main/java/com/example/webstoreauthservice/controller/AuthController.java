package com.example.webstoreauthservice.controller;

import com.example.webstoreauthservice.model.dto.UserDtoLogin;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для авторизации и регистрации пользователей. Предоставляет методы для регистрации
 * нового пользователя и входа в систему.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  /**
   * Регистрация нового пользователя.
   *
   * @param userDto Данные нового пользователя.
   * @return Объект {@link ResponseEntity} с информацией о зарегистрированном пользователе
   * {@link UserDtoRegister}.
   */
  @PostMapping("/register")
  public ResponseEntity<UserDtoRegister> register(@Valid @RequestBody UserDtoRegister userDto) {
    return ResponseEntity.ok(authService.register(userDto));
  }

  /**
   * Вход в систему пользователя.
   *
   * @param userDto Данные пользователя для входа.
   * @return Объект {@link ResponseEntity} с токеном аутентификации.
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody UserDtoLogin userDto) {
    return ResponseEntity.ok(authService.authenticate(userDto));
  }
}