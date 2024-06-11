package com.totvs.contasapagar.domain.auth.model;


import com.totvs.contasapagar.infrastructure.repository.auth.enums.UserRoleEnum;

public record Authentication(String login, String password, UserRoleEnum role) {}
