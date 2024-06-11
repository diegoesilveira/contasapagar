package com.totvs.contasapagar.infrastructure.exception;

public class UsuarioNaoEncontradoException extends ApiException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
