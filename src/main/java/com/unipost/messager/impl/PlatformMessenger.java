package com.unipost.messager.impl;

import com.unipost.messager.builder.PlatformMessengerTarget;

public interface PlatformMessenger {
    public void sendMessage(PlatformMessengerTarget platformMessengerTarget);
    public void sendCardMessage(PlatformMessengerTarget platformMessengerTarget);
}
