package com.example.webstoreauthservice.model.dto;

import lombok.Builder;

@Builder
public record ErrorResponseDto(String message) {

}