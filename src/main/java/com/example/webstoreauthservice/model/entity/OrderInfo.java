package com.example.webstoreauthservice.model.entity;

import com.example.commoncode.model.entity.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "order_info_id"))
@Table(name = "orders_info")
public class OrderInfo extends BaseEntity {

  /**
   * Уникальный идентификатор пользователя.
   */
  @Column(name = "user_uuid", nullable = false, unique = true)
  private UUID userUuid;

  /**
   * Коллекция с информацией о заказанных книгах.
   */
  @Builder.Default
  @ElementCollection
  @CollectionTable(name = "order_info_books", joinColumns = @JoinColumn(name = "order_info_id"))
  private Set<Book> books = new HashSet<>();
}