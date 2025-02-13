package com.unipost.unidocu.messaging;

public class Target {
    private String teamId;
    private String channelId;
    private String chatId;
    private Object message;

    public Target() {

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
}
