package com.totvs.contasapagar.domain.auth.service;

import com.totvs.contasapagar.domain.auth.model.Register;
import com.totvs.contasapagar.infrastructure.repository.auth.entity.UserEntity;
import com.totvs.contasapagar.infrastructure.repository.auth.UserRepository;
import com.totvs.contasapagar.infrastructure.exception.UsuarioNaoEncontradoException;
import com.totvs.contasapagar.infrastructure.util.AuthEncrypt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.totvs.contasapagar.infrastructure.util.Message.USUARIO_NAO_ENCONTRADO;


@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(USUARIO_NAO_ENCONTRADO));
        return userDetails;

    }

    public UserDetails findByLogin(Register register) {
        String encodedPassword = AuthEncrypt.encodePassword(register.password());
        return userRepository.findByLogin(register.login())
                .orElse(userRepository.save(new UserEntity(register.login(), encodedPassword, register.role())));
    }

}
