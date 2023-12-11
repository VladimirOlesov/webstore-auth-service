package com.example.webstoreauthservice.service;

import static com.example.webstoreauthservice.model.entity.User.Fields.email;
import static com.example.webstoreauthservice.model.entity.User.Fields.password;
import static com.example.webstoreauthservice.model.entity.User.Fields.username;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.commoncode.exception.DuplicateException;
import com.example.commoncode.model.enums.Role;
import com.example.webstoreauthservice.IntegrationTestBase;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.impl.AuthServiceImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
class AuthServiceIT extends IntegrationTestBase {

  private static final String NEW_USERNAME = "newUsername";
  private static final String NEW_EMAIL = "newEmail";
  private static final String PASSWORD_SUCCESSFUL = "";

  private final AuthServiceImpl authService;
  private final UserRepository userRepository;

  private void createTestUser() {
    userRepository.save(
        User.builder()
            .userUuid(UUID.randomUUID())
            .username(username)
            .email(email)
            .password(password)
            .role(Role.USER)
            .build());
  }

  @Test
  void registerUserWithDuplicateUsername() {
    createTestUser();

    assertThatThrownBy(() -> authService.register(UserDtoRegister.builder()
        .username(username)
        .email(NEW_EMAIL)
        .password(password)
        .build()))
        .isInstanceOf(DuplicateException.class)
        .hasMessage("Пользователь c таким именем или электронной почтой уже существует");

  }

  @Test
  void registerUserWithDuplicateEmail() {
    createTestUser();

    assertThatThrownBy(() -> authService.register(UserDtoRegister.builder()
        .username(NEW_USERNAME)
        .email(email)
        .password(password)
        .build()))
        .isInstanceOf(DuplicateException.class)
        .hasMessage("Пользователь c таким именем или электронной почтой уже существует");
  }

  @Test
  void registerUserSuccessfully() {
    UserDtoRegister userDto = UserDtoRegister.builder()
        .username(NEW_USERNAME)
        .email(NEW_EMAIL)
        .password(PASSWORD_SUCCESSFUL)
        .build();

    UserDtoRegister result = authService.register(userDto);

    assertThat(result)
        .isNotNull()
        .isEqualTo(userDto);
  }
}