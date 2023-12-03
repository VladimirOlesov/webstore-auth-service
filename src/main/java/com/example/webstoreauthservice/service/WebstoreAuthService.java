package com.example.webstoreauthservice.service;

import com.example.webstoreauthservice.model.dto.UserDto;
import java.util.UUID;

public interface WebstoreAuthService {

  UserDto getUserDtoByUuid(UUID uuid);
}