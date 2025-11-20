package com.arch.micro_service.auth_server.user.domain.vo;

import com.arch.micro_service.auth_server.user.domain.exception.EmailException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Email(
    /*
     * Email value
     */
    @Column(name = "email", unique = true) String value,
    /*
     * is the Email verified
     */
    @Column(name = "email_verified") boolean emailVerified

) {

  public Email {
    validate(value);
  }

  public static boolean isEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
  }

  public static Email create(String email) {
    return new Email(email, false);
  }

  public static Email create(String email, boolean verified) {
    return new Email(email, verified);
  }

  private static void validate(String email) {

    if (email == null || email.isBlank()) {
      throw EmailException.empty();
    }

    int atCount = email.length() - email.replace("@", "").length();
    if (atCount == 0)
      throw EmailException.missingAtSymbol();
    if (atCount > 1)
      throw EmailException.multipleAtSymbols();

    String[] parts = email.split("@", 2);
    String local = parts[0];
    String domain = parts[1];

    if (local.isEmpty())
      throw EmailException.emptyLocalPart();
    if (domain.isEmpty())
      throw EmailException.emptyDomainPart();
  }
}
