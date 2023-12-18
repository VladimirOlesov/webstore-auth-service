package com.example.webstoreauthservice.controller;

import com.example.webstoreauthservice.model.dto.UserDto;
import com.example.webstoreauthservice.service.UserService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/uuid/{uuid}")
  public UserDto getUserByUuid(@Valid @PathVariable UUID uuid) {
    return userService.getUserDtoByUuid(uuid);
  }

  @GetMapping("/username/{username}")
  public UserDto getUserDtoByUsername(@PathVariable String username) {
    return userService.getUserDtoByUsername(username);
  }
}