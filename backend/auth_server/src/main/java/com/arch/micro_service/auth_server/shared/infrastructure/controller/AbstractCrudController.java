package com.arch.micro_service.auth_server.shared.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

import org.springframework.http.ResponseEntity;

public interface AbstractCrudController<REQUEST extends AbstractCreateRequest, RESPONSE extends AbstractDetailResponse> {

  ResponseEntity<ApiResponse<List<RESPONSE>>> getAll();

  ResponseEntity<ApiResponse<RESPONSE>> get(String id);

  ResponseEntity<ApiResponse<String>> create(REQUEST request);

  ResponseEntity<ApiResponse<String>> update(String id, REQUEST request);

  ResponseEntity<ApiResponse<String>> delete(String id);

}
