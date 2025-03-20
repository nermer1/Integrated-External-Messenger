package com.unipost.messager.builder;

public class PlatformMessengerTarget {
    public String teamId;
    public String channelId;
    public String chatId;
    public String email;
    public Object message;

    public PlatformMessengerTarget() {

    }

    public String getTeamId() {
        return teamId;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChatId() {
        return chatId;
    }

    public Object getMessage() {
        return message;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
