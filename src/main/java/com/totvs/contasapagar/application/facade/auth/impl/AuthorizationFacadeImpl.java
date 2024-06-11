package com.totvs.contasapagar.application.facade.auth.impl;

import com.totvs.contasapagar.application.controller.auth.dto.LoginResponseDto;
import com.totvs.contasapagar.application.controller.auth.dto.RegisterDto;
import com.totvs.contasapagar.application.facade.auth.AuthorizationFacade;
import com.totvs.contasapagar.application.mapper.auth.AuthorizathionMapper;
import com.totvs.contasapagar.domain.facade.auth.AuthorizationDomainFacade;
import com.totvs.contasapagar.domain.auth.model.Register;
import com.totvs.contasapagar.domain.auth.service.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationFacadeImpl implements AuthorizationFacade {
    private final TokenService tokenService;

    private final AuthorizationDomainFacade domainFacade;
    private final AuthorizathionMapper mapper;

    public AuthorizationFacadeImpl(TokenService tokenService, AuthorizationDomainFacade domainFacade, AuthorizathionMapper mapper) {
        this.tokenService = tokenService;
        this.domainFacade = domainFacade;
        this.mapper = mapper;
    }

    @Override
    public LoginResponseDto login(String login, String password) {
        LoginResponseDto dtoLoginResponse = mapper.toDtoLoginResponse(domainFacade.login(login, password));
        return dtoLoginResponse;
    }

    @Override
    public UserDetails register(RegisterDto registerDto) {
        Register register = mapper.toDomain(registerDto);
        return domainFacade.register(register);
    }
}
