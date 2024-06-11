package com.totvs.contasapagar.domain.facade.auth;

import com.totvs.contasapagar.domain.auth.model.LoginResponse;
import com.totvs.contasapagar.domain.auth.model.Register;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizationDomainFacade {

    LoginResponse login(String login, String password);
    UserDetails register(Register register);
}
