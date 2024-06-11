package com.totvs.contasapagar.application.controller.auth;



import com.totvs.contasapagar.application.controller.auth.dto.AuthenticationDto;
import com.totvs.contasapagar.application.controller.auth.dto.LoginResponseDto;
import com.totvs.contasapagar.application.controller.auth.dto.RegisterDto;
import com.totvs.contasapagar.application.facade.auth.AuthorizationFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final AuthorizationFacade authorizationFacade;

    public AuthorizationController(AuthorizationFacade authorizationFacade) {
        this.authorizationFacade = authorizationFacade;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        LoginResponseDto loginResponseDto = authorizationFacade.login(authenticationDto.login(), authenticationDto.password());
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
        UserDetails userDetails = authorizationFacade.register(data);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }
}
