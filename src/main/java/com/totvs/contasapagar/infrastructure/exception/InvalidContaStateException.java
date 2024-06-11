package com.totvs.contasapagar.infrastructure.exception;

public class InvalidContaStateException extends RuntimeException {

    public InvalidContaStateException(String message) {
        super(message);
    }
}
