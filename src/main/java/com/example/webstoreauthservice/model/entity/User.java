package com.example.webstoreauthservice.model.entity;

import com.example.commoncode.model.entity.BaseEntity;
import com.example.commoncode.model.enums.Role;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Сущность, представляющая информацию о пользователе. Наследует поле 'id' от базового класса
 * BaseEntity и реализует интерфейс UserDetails.
 */
@Builder
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

  /**
   * Уникальный идентификатор пользователя.
   */
  @Column(name = "user_uuid", nullable = false, unique = true)
  private UUID userUuid;

  /**
   * Имя пользователя.
   */
  @NotBlank
  @Column(name = "username", nullable = false)
  private String username;

  /**
   * Пароль пользователя.
   */
  @NotBlank
  @Column(name = "password", nullable = false)
  private String password;

  /**
   * Адрес электронной почты пользователя.
   */
  @NotBlank
  @Column(name = "email", nullable = false)
  private String email;

  /**
   * Номер телефона пользователя.
   */
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * Дата регистрации пользователя.
   */
  @Column(name = "registration_date", columnDefinition = "timestamp")
  private LocalDateTime registrationDate;

  /**
   * Имя пользователя.
   */
  @Column(name = "first_name")
  private String firstName;

  /**
   * Фамилия пользователя.
   */
  @Column(name = "last_name")
  private String lastName;

  /**
   * Роль пользователя.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  /**
   * Получение коллекции ролей пользователя.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}