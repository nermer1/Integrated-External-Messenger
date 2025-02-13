package com.unipost.unidocu.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.conversations.ConversationsListRequest;
import com.slack.api.methods.response.conversations.ConversationsListResponse;
import com.slack.api.methods.response.users.UsersListResponse;
import com.slack.api.model.User;

import java.io.IOException;
import java.util.*;

public class SlackMessenger implements PlatformMessenger {
    private final Slack slack = Slack.getInstance();
    private final String token;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // blocks kit 관련 https://api.slack.com/tools/block-kit-builder

    public SlackMessenger(String token) {
        this.token = token;
    }

    @Override
    public void sendMessage(Target target) {
        try {
            Object message = target.getMessage();
            if(!(message instanceof String)) throw new IllegalArgumentException("Invalid message type");
            slack.methods(token).chatPostMessage(ChatPostMessageRequest.builder()
                    .channel(target.getChannelId())
                    .text((String) message)
                    .build());
        } catch(SlackApiException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendCardMessage(Target target) {
        try {
            ChatPostMessageRequest request;
            Object message = target.getMessage();

            if(message instanceof String) {
                request = ChatPostMessageRequest.builder()
                        .channel(target.getChannelId())
                        .blocksAsString((String) message)
                        .build();
            } else if(message instanceof List) {
                request = ChatPostMessageRequest.builder()
                        .channel(target.getChannelId())
                        .blocksAsString(objectMapper.writeValueAsString(message))
                        .build();
            } else {
                throw new IllegalArgumentException("Invalid message type");
            }

            slack.methods(token).chatPostMessage(request);
        } catch(IOException | SlackApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getChannels() {
        String nextCursor = null;
        try {
            do {
                ConversationsListResponse response = slack.methods(token).conversationsList(
                        ConversationsListRequest.builder()
                                .limit(200)
                                .cursor(nextCursor)
                                .build()
                );

                if (response.isOk()) {
                    // 채널 ID 및 이름 출력
                    response.getChannels().forEach(channel -> {
                        System.out.println("Channel ID: " + channel.getId() + ", Name: " + channel.getName());
                    });

                    nextCursor = response.getResponseMetadata().getNextCursor(); // 다음 페이지 처리
                } else {
                    System.out.println("Error: " + response.getError());
                    break;
                }
            } while (nextCursor != null && !nextCursor.isEmpty());

        } catch(SlackApiException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getMembers() {
        try {
            UsersListResponse usersListResponse = slack.methods(token).usersList(req -> req);

            if (usersListResponse.isOk()) {
                for (User user : usersListResponse.getMembers()) {
                    System.out.println("profile: " + user);
                }
            } else {
                System.out.println("Error: " + usersListResponse.getError());
            }
        } catch(SlackApiException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
