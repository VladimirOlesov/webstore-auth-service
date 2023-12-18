package com.example.webstoreauthservice.model.dto;

import com.example.webstoreauthservice.model.entity.Book;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderDto(
    UUID userUuid,
    Set<Book> books) {

}