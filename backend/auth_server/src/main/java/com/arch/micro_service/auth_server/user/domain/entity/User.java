package com.arch.micro_service.auth_server.user.domain.entity;

import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.shared.domain.entity.BasedEntity;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.domain.vo.Password;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User entity
 * 
 * @author SRIRAM
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@ToString
public class User extends BasedEntity {

  /**
   * User name
   */
  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  /**
   * password value object
   */
  @Embedded
  private Password password;

  /**
   * Email value object
   */
  @Embedded
  private Email email;

  /**
   * User gender
   */
  @Column(name = "gender", nullable = false, unique = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;
  /**
   * User role
   */
  @Column(name = "role", nullable = false)
  private String role;

  /**
   * Is account not Expired
   */
  @Column(name = "account_non_expired")
  private boolean accountNonExpired;

  /**
   * Is account not locked
   */
  @Column(name = "account_non_locked")
  private boolean accountNonLocked;

  /**
   * Is account enabled
   */
  @Column(name = "enabled")
  private boolean enabled;

  /**
   * do user have password
   */
  public boolean isPasswordPresent() {
    return this.password == null;
  }

  /**
   * update new password
   */
  public void updatePassword(String password) {
    this.password = Password.create(password);
  }

  /**
   * delete the password
   */
  public void resetPassword() {
    this.password = null;
  }

  /**
   * If user is locked unlock it
   */
  public void toggleLock() {
    this.accountNonLocked = !this.accountNonLocked;
  }

  /**
   * is the Email
   */
  public boolean isVerified() {
    return this.email.emailVerified();
  }

  /**
   * verify the Email
   */
  public void verifyEmail() {
    this.email = Email.create(this.email.value(), true);
  }
}
