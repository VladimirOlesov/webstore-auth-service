package com.example.webstoreauthservice.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderMapper {

  private final PasswordEncoder passwordEncoder;

  @CustomMapping
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}