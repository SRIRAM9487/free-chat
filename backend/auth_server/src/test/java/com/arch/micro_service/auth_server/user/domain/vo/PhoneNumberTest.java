package com.arch.micro_service.auth_server.user.domain.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import com.arch.micro_service.auth_server.user.domain.exception.PhoneNumberException;
import com.arch.micro_service.auth_server.user.domain.exception.type.PhoneNumberExceptionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

  private PhoneNumber testNumber;
  private String validNumber = "12345678";

  @BeforeEach
  void setup() {
    testNumber = PhoneNumber.create(validNumber);
  }

  @Test
  void validPhoneNumber() {
    PhoneNumber phone = PhoneNumber.create(validNumber);
    assertEquals(validNumber, phone.value());
  }

  @Test
  void nullOrEmptyPhoneNumber() {

    PhoneNumberException exception = assertThrowsExactly(PhoneNumberException.class,
        () -> PhoneNumber.create(null));
    assertEquals(PhoneNumberExceptionType.EMPTY_PHONE.name(), exception.getCode());

    exception = assertThrowsExactly(PhoneNumberException.class,
        () -> PhoneNumber.create(""));
    assertEquals(PhoneNumberExceptionType.EMPTY_PHONE.name(), exception.getCode());

    exception = assertThrowsExactly(PhoneNumberException.class,
        () -> PhoneNumber.create("   "));
    assertEquals(PhoneNumberExceptionType.EMPTY_PHONE.name(), exception.getCode());
  }

  @Test
  void tooShortPhoneNumber() {
    PhoneNumberException exception = assertThrowsExactly(PhoneNumberException.class,
        () -> PhoneNumber.create("1234567"));
    assertEquals(PhoneNumberExceptionType.TOO_SHORT.name(), exception.getCode());
  }

  @Test
  void tooLongPhoneNumber() {
    PhoneNumberException exception = assertThrowsExactly(PhoneNumberException.class,
        () -> PhoneNumber.create("1234567890123456"));
    assertEquals(PhoneNumberExceptionType.TOO_LONG.name(), exception.getCode());
  }

  @Test
  void validateMethodDirectly() {
    testNumber.validate(validNumber);

    PhoneNumberException exception = assertThrowsExactly(PhoneNumberException.class,
        () -> testNumber.validate(null));
    assertEquals(PhoneNumberExceptionType.EMPTY_PHONE.name(), exception.getCode());

    exception = assertThrowsExactly(PhoneNumberException.class,
        () -> testNumber.validate("1234"));

    assertEquals(PhoneNumberExceptionType.TOO_SHORT.name(), exception.getCode());
    exception = assertThrowsExactly(PhoneNumberException.class,
        () -> testNumber.validate("12345678901234567890"));
    assertEquals(PhoneNumberExceptionType.TOO_LONG.name(), exception.getCode());
  }

}
