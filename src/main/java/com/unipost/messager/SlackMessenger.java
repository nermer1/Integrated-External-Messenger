package com.unipost.messager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;
import com.unipost.messager.builder.PlatformMessengerTarget;
import com.unipost.messager.exception.SlackIntegrationException;
import com.unipost.messager.impl.PlatformMessenger;

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

    public String getId(PlatformMessengerTarget platformMessengerTarget) {
        String email = platformMessengerTarget.getEmail();
        String channelId = platformMessengerTarget.getChannelId();

        if((email == null || email.isEmpty()) && (channelId == null || channelId.isEmpty())) throw new IllegalArgumentException("Both email and channelId cannot be empty or null");
        if(channelId != null && !channelId.isEmpty()) return channelId;
        else return this.getIdByEmail(email);
    }

    @Override
    public void sendMessage(PlatformMessengerTarget platformMessengerTarget) {
        try {
            Object message = platformMessengerTarget.getMessage();
            if(!(message instanceof String)) throw new IllegalArgumentException("Invalid message type");
            slack.methods(token).chatPostMessage(ChatPostMessageRequest.builder()
                    .channel(this.getId(platformMessengerTarget))
                    .text((String) message)
                    .build());
        } catch(SlackApiException | IOException e) {
            throw new SlackIntegrationException(e.getMessage());
        }
    }

    @Override
    public void sendCardMessage(PlatformMessengerTarget platformMessengerTarget) {
        try {
            Object message = platformMessengerTarget.getMessage();
            String blocksJson;

            if(message instanceof String) {
                blocksJson = (String) message;
            } else if(message instanceof List) {
                blocksJson = objectMapper.writeValueAsString(message);
            } else {
                throw new IllegalArgumentException("Invalid message type");
            }

            slack.methods(token).chatPostMessage(
                    ChatPostMessageRequest.builder()
                            .channel(this.getId(platformMessengerTarget))
                            .blocksAsString(blocksJson)
                            .build()
            );
        } catch(IOException | SlackApiException e) {
            throw new SlackIntegrationException(e.getMessage());
        }
    }

    public String getIdByEmail(String email) {
        try {
            UsersLookupByEmailResponse response = slack.methods(token).usersLookupByEmail(r -> r.email(email));
            return response.getUser().getId();
        } catch(SlackApiException | IOException e) {
            throw new SlackIntegrationException(e.getMessage());
        }
    }
    
    
    // 일단 필요 없을 듯
    /*@Override
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
            throw new SlackIntegrationException(e.getMessage());
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
            throw new SlackIntegrationException(e.getMessage());
        }
    }

    @Override
    public User getUser() {
        return new User();
    }
    */
}
