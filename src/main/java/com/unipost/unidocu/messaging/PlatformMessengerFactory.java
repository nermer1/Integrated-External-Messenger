package com.unipost.unidocu.messaging;

public class PlatformMessengerFactory {
    public PlatformMessenger create(PlatformMessengerType type, String token) {
        switch (type) {
            case SLACK:
                return new SlackMessenger(token);
            default:
                throw new NullPointerException("");
        }
    }
}
