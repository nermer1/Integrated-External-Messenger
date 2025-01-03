package com.unipost.unidocu.messaging;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.conversations.ConversationsListRequest;
import com.slack.api.methods.response.conversations.ConversationsListResponse;
import com.slack.api.methods.response.users.UsersListResponse;
import com.slack.api.model.User;
import com.slack.api.model.block.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlackMessenger implements PlatformMessenger {
    private final Slack slack = Slack.getInstance();
    private final String token;

    // blocks kit 관련 https://api.slack.com/tools/block-kit-builder

    public SlackMessenger(String token) {
        //slack.methods()
        this.token = token;
    }

    @Override
    public void sendMessage(String message) {
        // 메시지: text
        try {
            slack.methods(token).chatPostMessage(ChatPostMessageRequest.builder()
                    .channel("C053UFTFH97")
                    .text(message)
                    .build());
        } catch(SlackApiException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void test() {
        //ApiTestResponse response = slack
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
                    System.err.println("Error: " + response.getError());
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

    public void sendCardMessage() {
        /*SectionBlock headerBlock = Blocks.section(s -> s.text(MarkdownTextObject.builder()
                .text("문자열")
                .build()));
        DividerBlock divider = Blocks.divider();
        SectionBlock messageBlock = Blocks.section(s -> s.text(MarkdownTextObject.builder()
                .text("디바이드?")
                .build()));

        Blocks.section(s -> s.text(PlainTextObject.builder().text("?").build()));*/


        List<LayoutBlock> layoutBlocks = new ArrayList<>();

        try {
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("")
                    .blocks(Arrays.asList())
                    .build();

            slack.methods(token).chatPostMessage(request);
        } catch(IOException | SlackApiException e) {
            e.printStackTrace();
        }
    }
}
