package com.totvs.contasapagar.application.facade.auth;


import com.totvs.contasapagar.application.controller.auth.dto.LoginResponseDto;
import com.totvs.contasapagar.application.controller.auth.dto.RegisterDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizationFacade {
    LoginResponseDto login(String login, String password);
    UserDetails register(RegisterDto registerDto);
}
