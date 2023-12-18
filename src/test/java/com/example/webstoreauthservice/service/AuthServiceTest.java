package com.example.webstoreauthservice.service;

import static com.example.webstoreauthservice.model.dto.UserDtoRegister.Fields.email;
import static com.example.webstoreauthservice.model.dto.UserDtoRegister.Fields.password;
import static com.example.webstoreauthservice.model.dto.UserDtoRegister.Fields.username;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.example.commoncode.exception.DuplicateException;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  private static final String EXISTING_USERNAME = "existingUsername";
  private static final String EXISTING_EMAIL = "existingEmail";

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserService userService;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtService jwtService;

  @InjectMocks
  private AuthServiceImpl authService;

  private UserDtoRegister createTestUserDto(String username, String email) {
    return UserDtoRegister.builder()
        .username(username)
        .email(email)
        .password(password)
        .build();
  }

  @Test
  void registerUserWithDuplicateUsername() {
    doReturn(true).when(userRepository).existsByUsername(EXISTING_USERNAME);

    assertThatThrownBy(
        () -> authService.register(createTestUserDto(EXISTING_USERNAME, email)))
        .isInstanceOf(DuplicateException.class)
        .hasMessage("Пользователь c таким именем или электронной почтой уже существует");

    verify(userRepository).existsByUsername(EXISTING_USERNAME);
  }

  @Test
  void registerUserWithDuplicateEmail() {
    doReturn(false).when(userRepository).existsByUsername(username);
    doReturn(true).when(userRepository).existsByEmail(EXISTING_EMAIL);

    assertThatThrownBy(
        () -> authService.register(createTestUserDto(username, EXISTING_EMAIL)))
        .isInstanceOf(DuplicateException.class)
        .hasMessage("Пользователь c таким именем или электронной почтой уже существует");

    verify(userRepository).existsByUsername(username);
    verify(userRepository).existsByEmail(EXISTING_EMAIL);
  }

  @Test
  void registerUserSuccessfully() {
    UserDtoRegister userDto = createTestUserDto(username, email);

    doReturn(false).when(userRepository).existsByUsername(username);
    doReturn(false).when(userRepository).existsByEmail(email);
    doReturn(userDto).when(userService).save(userDto);

    UserDtoRegister result = authService.register(userDto);

    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(userDto);

    verify(userRepository).existsByUsername(username);
    verify(userRepository).existsByEmail(email);
    verify(userService).save(userDto);
  }

  @AfterEach
  void verifyInteractions() {
    verifyNoMoreInteractions(userRepository, userService, authenticationManager, jwtService);
  }
}