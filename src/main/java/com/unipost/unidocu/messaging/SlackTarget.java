package com.unipost.unidocu.messaging;

public class SlackTarget extends Target {
    private String channelId;
    private Object message;

    public SlackTarget(Builder builder) {
        this.channelId = builder.channelId;
        this.message = builder.message;
    }

    public String getChannelId() {
        return channelId;
    }

    public Object getMessage() {
        return message;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {
        private String channelId;
        private Object message;

        public Builder channelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public Builder message(Object message) {
            this.message = message;
            return this;
        }

        public SlackTarget build() {
            return new SlackTarget(this);
        }
    }
}
