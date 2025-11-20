package com.arch.micro_service.auth_server.user.domain.vo;

import com.arch.micro_service.auth_server.user.domain.exception.PasswordException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Password(
    /*
     * hashed password
     */
    @Column(name = "password") String value) {

  public Password {
    if (value == null || value.isBlank())
      throw PasswordException.empty();
  }

  public static Password create(String value) {
    return new Password(value);
  }

  /*
   * Custom raw password validator
   */
  public static boolean isValid(String hash) {
    if (hash == null || hash.isEmpty())
      throw PasswordException.empty();

    if (hash.length() < 8)
      throw PasswordException.requireAtleastEightCharacter();

    if (!hash.matches(".*[A-Z].*"))
      throw PasswordException.requireAtleastOneCapitalLetter();

    if (!hash.matches(".*[a-z].*"))
      throw PasswordException.requireAtleastOneLowerCaseLetter();

    if (!hash.matches(".*[0-9].*"))
      throw PasswordException.requireAtleastOneNumber();

    return true;
  }

}
