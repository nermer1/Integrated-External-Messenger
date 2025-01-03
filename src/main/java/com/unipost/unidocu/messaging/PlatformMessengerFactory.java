package com.unipost.unidocu.messaging;

public class PlatformMessengerFactory {
    public PlatformMessenger create(PlatformMessengerType type, String token) {
        switch (type) {
            case SLACK:
                return new SlackMessenger(token);
            case TEAMS:
                return new TeamsMessenger();
            default:
                throw new NullPointerException("");
        }
    }
}
