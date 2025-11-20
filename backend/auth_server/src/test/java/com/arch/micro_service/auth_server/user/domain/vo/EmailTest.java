package com.arch.micro_service.auth_server.user.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.arch.micro_service.auth_server.user.domain.exception.EmailException;
import com.arch.micro_service.auth_server.user.domain.exception.type.EmailExceptionType;

import org.junit.jupiter.api.BeforeEach;

class EmailTest {

  private Email email;
  private Email verifiedEmail;
  private String test = "test@gmail.com";

  @BeforeEach
  void setup() {
    email = new Email(test, false);
    verifiedEmail = new Email(test, true);
  }

  @Test
  void newEmail() {

    Email newEmail = Email.create(test);
    assertEquals(email, newEmail, "Email created not verified");

    Email unVerifiedEmail = Email.create(test, true);
    assertEquals(verifiedEmail, unVerifiedEmail, "Email created and verified");
  }

  @Test
  void isEmail() {

    assertTrue(Email.isEmail(test), "Email is valid");

    assertFalse(Email.isEmail(test.replace("@", "")), "Emaile is not valid");

    assertFalse(Email.isEmail(test.replace(".com", "")), "Emaile is not valid");

  }

  @Test
  void emptyEmail() {

    EmailException exception = assertThrowsExactly(EmailException.class, () -> Email.create(""));
    assertEquals(EmailExceptionType.NULL_EMAIL.name(), exception.getCode(), "Email cannot be empty");

    exception = assertThrowsExactly(EmailException.class, () -> Email.create(null));
    assertEquals(EmailExceptionType.NULL_EMAIL.name(), exception.getCode(), "Email cannot be null");

  }

  @Test
  void missingAtSymbol() {
    EmailException exception = assertThrowsExactly(EmailException.class, () -> Email.create(test.replace("@", "")));
    assertEquals(EmailExceptionType.MISSING_AT_SYMBOL.name(), exception.getCode());
  }

  @Test
  void multipleAtSymbol() {
    EmailException exception = assertThrowsExactly(EmailException.class, () -> Email.create(test.replace("@", "@@@@")));
    assertEquals(EmailExceptionType.MULTIPLE_AT_SYMBOLS.name(), exception.getCode());
  }

  @Test
  void emtpyLocalPart() {
    EmailException exception = assertThrowsExactly(EmailException.class, () -> Email.create(test.replace("test", "")));
    assertEquals(EmailExceptionType.EMPTY_LOCAL_PART.name(), exception.getCode());
  }

  @Test
  void emtpyDomainPart() {
    EmailException exception = assertThrowsExactly(EmailException.class,
        () -> Email.create(test.replace("gmail.com", "")));
    assertEquals(EmailExceptionType.EMPTY_DOMAIN_PART.name(), exception.getCode());
  }

}
