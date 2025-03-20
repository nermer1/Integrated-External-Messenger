package com.unipost.messager.factory;

import com.unipost.messager.type.PlatformMessengerType;
import com.unipost.messager.impl.PlatformMessenger;
import com.unipost.messager.SlackMessenger;

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
