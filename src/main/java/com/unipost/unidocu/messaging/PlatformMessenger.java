package com.unipost.unidocu.messaging;

public interface PlatformMessenger {
    public void sendMessage(Target target);
    public void getChannels();
    public void getMembers();
    public void sendCardMessage(Target target);
}
