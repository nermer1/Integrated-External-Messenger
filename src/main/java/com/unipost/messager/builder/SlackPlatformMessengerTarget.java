package com.unipost.messager.builder;

public class SlackPlatformMessengerTarget extends PlatformMessengerTarget {

    /**
     * 슬랙 메시지 전송을 위한 대상 정보
     *
     * channelId 채널 아이디(채널 또는 유저)
     * message 전송할 메시지
     * email 이메일
     *
     *
     */
    public SlackPlatformMessengerTarget(Builder builder) {
        this.channelId = builder.channelId;
        this.message = builder.message;
        this.email = builder.email;
    }

    public static class Builder {
        private String channelId;
        private String email;
        private Object message;

        public Builder channelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public Builder message(Object message) {
            this.message = message;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public SlackPlatformMessengerTarget build() {
            return new SlackPlatformMessengerTarget(this);
        }
    }
}
