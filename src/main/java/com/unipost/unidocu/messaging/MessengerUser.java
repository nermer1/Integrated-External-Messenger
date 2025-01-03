package com.unipost.unidocu.messaging;

public class MessengerUser {
    private final String username;
    private final String email;
    private final String id;

    private MessengerUser(Builder userBuilder) {
        this.username = userBuilder.username;
        this.email = userBuilder.email;
        this.id = userBuilder.id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MessengerUser [username=" + username + ", email=" + email + ", id=" + id + "]";
    }

    public static class Builder {
        private String username;
        private String email;
        private String id;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public MessengerUser build() {
            return new MessengerUser(this);
        }
    }
}
