package com.example.webstoreauthservice.service.impl;

import com.example.commoncode.model.enums.Role;
import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.model.dto.UserDtoRegister;
import com.example.webstoreauthservice.model.entity.User;
import com.example.webstoreauthservice.model.mapper.UserMapper;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса {@link UserService} для работы с пользователями.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  /**
   * Получение пользователя по его имени.
   *
   * @param username Имя пользователя.
   * @return Объект {@link User}.
   * @throws EntityNotFoundException, если пользователь не найден.
   */
  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
  }

  /**
   * Сохранение нового пользователя на основе предоставленных данных.
   *
   * @param userDtoRegister Объект {@link UserDtoRegister} с данными для регистрации.
   * @return Объект {@link UserDtoRegister} зарегистрированного пользователя.
   */
  @Override
  @Transactional
  public UserDtoRegister save(UserDtoRegister userDtoRegister) {

    User user = userMapper.userDtoToUser(userDtoRegister);
    user.setRole(Role.USER);
    user.setRegistrationDate(LocalDateTime.now());
    user.setUserUuid(UUID.randomUUID());
    userRepository.save(user);
    return userMapper.convertToDto(user);
  }

  /**
   * Получение объекта {@link UserDetailsService} для Spring Security.
   *
   * @return Объект {@link UserDetailsService}.
   */
  @Override
  public UserDetailsService userDetailsService() {
    return this::getUserByUsername;
  }

  /**
   * Получение dto пользователя по его имени.
   *
   * @param username Имя пользователя.
   * @return Объект {@link UserDto}.
   */
  @Override
  public UserDto getUserDtoByUsername(String username) {
    return userMapper.toUserDto(getUserByUsername(username));
  }

  /**
   * Получение dto пользователя по его uuid.
   *
   * @param uuid UUID пользователя.
   * @return Объект {@link UserDto}.
   * @throws EntityNotFoundException, если пользователь не найден.
   */
  @Override
  public UserDto getUserDtoByUuid(UUID uuid) {
    return userMapper.toUserDto(userRepository.findByUserUuid(uuid)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));
  }
}