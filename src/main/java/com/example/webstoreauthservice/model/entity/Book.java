package com.example.webstoreauthservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Встроенная сущность для представления информации о книге.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Book {

  /**
   * Идентификатор книги.
   */
  @Column(name = "book_id", nullable = false)
  private Long id;

  /**
   * Название книги.
   */
  @Column(name = "title", nullable = false)
  private String title;
}