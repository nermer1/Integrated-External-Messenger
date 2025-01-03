package com.unipost.unidocu.messaging;

public interface PlatformMessenger {
    public void sendMessage(String message);
    public void getChannels();
    public void test();
    public void getMembers();
}
