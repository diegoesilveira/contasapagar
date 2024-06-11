package com.totvs.contasapagar.domain.facade.auth.impl;

import com.totvs.contasapagar.domain.auth.service.AuthService;
import com.totvs.contasapagar.domain.facade.auth.AuthorizationDomainFacade;
import com.totvs.contasapagar.domain.auth.model.LoginResponse;
import com.totvs.contasapagar.domain.auth.model.Register;
import com.totvs.contasapagar.domain.auth.service.AuthorizationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationDomainFacadeImpl implements AuthorizationDomainFacade {

    private final AuthService authService;
    private final AuthorizationService service;

    public AuthorizationDomainFacadeImpl(AuthService authService, AuthorizationService service) {
        this.authService = authService;
        this.service = service;
    }
    @Override
    public LoginResponse login(String login, String password) {
        return authService.authenticationToken(login, password);
    }

    @Override
    public UserDetails register(Register register) {
        return service.findByLogin(register);
    }
}
