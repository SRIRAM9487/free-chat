package com.arch.micro_service.auth_server.shared.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

public interface AbstractCrudController<REQUEST extends AbstractCreateRequest> {

  ApiResponse<List<AbstractDetailResponse>> getAll();

  ApiResponse<AbstractDetailResponse> get(String id);

  ApiResponse<String> create(REQUEST request);

  ApiResponse<String> update(String id, REQUEST request);

  ApiResponse<String> create(String id);

}
