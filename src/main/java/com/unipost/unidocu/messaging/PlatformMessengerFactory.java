package com.unipost.unidocu.messaging;

public class PlatformMessengerFactory {
    public PlatformMessenger create(PlatformMessengerType type, String token) {
        switch (type) {
            case TEAMS:
                return new TeamsMessenger();
            default:
                throw new NullPointerException("");
        }
    }
}
