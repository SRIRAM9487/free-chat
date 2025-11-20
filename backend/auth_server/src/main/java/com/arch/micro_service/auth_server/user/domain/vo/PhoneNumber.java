package com.arch.micro_service.auth_server.user.domain.vo;

import com.arch.micro_service.auth_server.user.domain.exception.PhoneNumberException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(
    /*
     * Phone number
     */
    @Column(name = "phone", nullable = false) String value) {

  public PhoneNumber {
    validate(value);
  }

  public static PhoneNumber create(String number) {
    return new PhoneNumber(number);
  }

  public void validate(String number) {

    if (number == null || number.isBlank())
      throw PhoneNumberException.empty();

    if (number.length() < 8)
      throw PhoneNumberException.tooShort();

    if (number.length() > 15)
      throw PhoneNumberException.tooLong();

  }

}
