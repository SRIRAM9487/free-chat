package com.arch.micro_service.auth_server.user.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;
import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.user.application.service.impl.UserEmailServiceImpl;
import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.EmailException;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.domain.vo.Password;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserEmailServiceTest {

  @Mock
  private TokenGeneratorService tokenGeneratorService;

  @Mock
  private EmailEventPublisher emailEventPublisher;

  @Mock
  private UserFindUseCase userFindUseCase;

  @Mock
  private CacheService cacheService;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserEmailServiceImpl emailService;

  private User user;
  private final String token = "HTIS4821798274";

  @BeforeEach
  void setup() {
    user = User
        .builder()
        .userName("TEST")
        .password(Password.create("HAHSEDPASSWORD"))
        .email(Email.create("test@gmail.com", true))
        .gender(Gender.MALE)
        .accountNonLocked(true)
        .accountNonExpired(true)
        .enabled(true)
        .build();

    user.setName("test");
    user.setId(1L);
    user.setCreatedAt(LocalDateTime.now());
    user.setCreatedBy("TESTER");
  }

  @Test
  void sendVerification_ShouldPublishEvent_AndSaveToken() {

    when(tokenGeneratorService.generateToken()).thenReturn(token);
    when(userFindUseCase.findByUserId("1")).thenReturn(user);

    emailService.sendVerification("1");

    ArgumentCaptor<EmailVerificationEvent> captor = ArgumentCaptor.forClass(EmailVerificationEvent.class);

    verify(emailEventPublisher, times(1)).publishVerificationEmail(captor.capture());
    EmailVerificationEvent ev = captor.getValue();

    assertEquals(user.getEmail().value(), ev.email());
    assertEquals("1", ev.userId());
    assertEquals(token, ev.token());

    verify(cacheService, times(1)).save(user.getEmail().value(), token);
  }

  @Test
  void verifyEmail_ShouldMarkUserVerified_WhenTokenCorrect() {
    when(userFindUseCase.findByEmail(user.getEmail().value()))
        .thenReturn(user);
    when(cacheService.retrive(user.getEmail().value()))
        .thenReturn(token);

    emailService.verifyEmail(user.getEmail().value(), token);

    verify(userRepository, times(1)).save(user);

    verify(cacheService, times(1)).remove(user.getEmail().value());
  }

  @Test
  void verifyEmail_ShouldThrow_WhenTokenIncorrect() {
    when(userFindUseCase.findByEmail(user.getEmail().value()))
        .thenReturn(user);
    when(cacheService.retrive(user.getEmail().value()))
        .thenReturn("ANOTHER_TOKEN");

    assertThrows(
        EmailException.class,
        () -> emailService.verifyEmail(user.getEmail().value(), token));

    verify(userRepository, never()).save(any());
    verify(cacheService, never()).remove(any());
  }
}
