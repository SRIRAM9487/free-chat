package com.arch.micro_service.chat_server.shared.infrastructure.exception;

import com.arch.micro_service.chat_server.shared.domain.CustomDataIntegrityException;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DataIntegerityViolationController {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiException> handleDataIntegertiyViolation(DataIntegrityViolationException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiException.create(extractData(exception), request));
  }

  private CustomDataIntegrityException extractData(DataIntegrityViolationException exception) {
    if (exception.getLocalizedMessage().contains("chatters_user_id_key"))
      return CustomDataIntegrityException.uniqueUserId();
    return CustomDataIntegrityException.uniqueContraint();
  }

}
