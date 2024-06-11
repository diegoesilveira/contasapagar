package com.totvs.contasapagar.application.controller.auth.dto;

import com.totvs.contasapagar.infrastructure.repository.auth.enums.UserRoleEnum;

public record AuthenticationDto(String login, String password, UserRoleEnum role) {}
