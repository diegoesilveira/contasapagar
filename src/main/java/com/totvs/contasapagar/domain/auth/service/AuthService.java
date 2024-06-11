package com.totvs.contasapagar.domain.auth.service;

import com.totvs.contasapagar.domain.auth.model.LoginResponse;
import com.totvs.contasapagar.infrastructure.repository.auth.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, AuthorizationService authorizationService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.tokenService = tokenService;
    }


    public LoginResponse authenticationToken(String login, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        Authentication auth = this.authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new LoginResponse(token);
    }

}
