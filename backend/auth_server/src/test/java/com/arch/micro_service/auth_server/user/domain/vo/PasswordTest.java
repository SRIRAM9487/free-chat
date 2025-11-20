package com.arch.micro_service.auth_server.user.domain.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arch.micro_service.auth_server.user.domain.exception.PasswordException;
import com.arch.micro_service.auth_server.user.domain.exception.type.PasswordExceptionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordTest {

  private Password testPassword;
  private String password = "Test1234passwor";

  @BeforeEach
  void setup() {
    testPassword = Password.create(password);
  }

  @Test
  void password() {
    assertTrue(Password.isValid(password), "Valid password");
    assertEquals(testPassword, Password.create(password));
  }

  @Test
  void nullPassword() {

    PasswordException exception = assertThrowsExactly(PasswordException.class, () -> Password.create(null));
    assertEquals(PasswordExceptionType.NULL_PASSWORD.name(), exception.getCode(), "Password cannot be null");

    exception = assertThrowsExactly(PasswordException.class, () -> Password.create(""));
    assertEquals(PasswordExceptionType.NULL_PASSWORD.name(), exception.getCode(), "Password cannot be Empty");

    exception = assertThrowsExactly(PasswordException.class, () -> Password.isValid(null));
    assertEquals(PasswordExceptionType.NULL_PASSWORD.name(), exception.getCode(), "Password cannot be null");

    exception = assertThrowsExactly(PasswordException.class, () -> Password.isValid(""));
    assertEquals(PasswordExceptionType.NULL_PASSWORD.name(), exception.getCode(), "Password cannot be Empty");
  }

  @Test
  void passwordLength() {

    PasswordException exception = assertThrowsExactly(PasswordException.class,
        () -> Password.isValid(password.substring(0, 4)));

    assertEquals(PasswordExceptionType.ATLEAST_EIGHT_CHARACTERS.name(), exception.getCode(), "Length>8");
  }

  @Test
  void strength() {

    PasswordException exception = assertThrowsExactly(PasswordException.class,
        () -> Password.isValid(password.toLowerCase()));

    assertEquals(PasswordExceptionType.ATLEAST_ONE_CAPITAL_LETTER.name(), exception.getCode(),
        "Atleast one capital letter");

    exception = assertThrowsExactly(PasswordException.class,
        () -> Password.isValid(password.toUpperCase()));

    assertEquals(PasswordExceptionType.ATLEAST_ONE_LOWERCASE_LETTER.name(), exception.getCode(),
        "Atleast one lower case letter");

    exception = assertThrowsExactly(PasswordException.class,
        () -> Password.isValid(password.replace("1234", "hello")));

    assertEquals(PasswordExceptionType.ATLEAST_ONE_NUMBER.name(), exception.getCode(), "Atleast one number");
  }

}
