package com.example.webstoreauthservice.service.impl;

import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.model.mapper.UserMapper;
import com.example.webstoreauthservice.repository.UserRepository;
import com.example.webstoreauthservice.service.WebstoreAuthService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebstoreAuthServiceImpl implements WebstoreAuthService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public UserDto getUserDtoByUuid(UUID uuid) {
    return userMapper.toUserDto(userRepository.findByUserUuid(uuid)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));
  }
}
