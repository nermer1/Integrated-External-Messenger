package com.unipost.messager.exception;

public class SlackIntegrationException extends RuntimeException {
    public SlackIntegrationException(String message) {
        super(message);
    }

    public SlackIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
