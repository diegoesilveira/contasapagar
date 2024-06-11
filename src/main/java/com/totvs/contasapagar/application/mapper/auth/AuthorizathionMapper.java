package com.totvs.contasapagar.application.mapper.auth;


import com.totvs.contasapagar.application.controller.auth.dto.LoginResponseDto;
import com.totvs.contasapagar.application.controller.auth.dto.RegisterDto;
import com.totvs.contasapagar.domain.auth.model.LoginResponse;
import com.totvs.contasapagar.domain.auth.model.Register;
import org.springframework.stereotype.Component;

@Component
public class AuthorizathionMapper {

    public Register toDomain(RegisterDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        return new Register(
                registerDto.login(),
                registerDto.password(),
                registerDto.role()
        );
    }

    public LoginResponseDto toDtoLoginResponse(LoginResponse loginResponse) {
        if (loginResponse == null) {
            return null;
        }
        return new LoginResponseDto(
                loginResponse.token()
        );
    }
}
