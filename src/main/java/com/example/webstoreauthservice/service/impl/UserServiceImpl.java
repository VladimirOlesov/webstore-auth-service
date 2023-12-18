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

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
  }

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

  @Override
  public UserDetailsService userDetailsService() {
    return this::getUserByUsername;
  }

  @Override
  public UserDto getUserDtoByUsername(String username) {
    return userMapper.toUserDto(getUserByUsername(username));
  }

  @Override
  public UserDto getUserDtoByUuid(UUID uuid) {
    return userMapper.toUserDto(userRepository.findByUserUuid(uuid)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));
  }
}