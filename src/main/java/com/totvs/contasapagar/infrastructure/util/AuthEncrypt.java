package com.totvs.contasapagar.infrastructure.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthEncrypt {

    public static String encodePassword(String password) {
        try {
            return new BCryptPasswordEncoder().encode(password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
