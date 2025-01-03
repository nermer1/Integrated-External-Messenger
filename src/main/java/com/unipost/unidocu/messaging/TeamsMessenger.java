package com.unipost.unidocu.messaging;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.serviceclient.GraphServiceClient;

public class TeamsMessenger implements PlatformMessenger {

    public TeamsMessenger() {
        // 토큰, 클라이언트 아이디?
        // 팀즈 관련 연동?

        // 개발자 환경을 제공 안함, 있었는데 잠정적으로 막힌 듯 유료 팀즈 가입해야됨, 예제 부족, 이전 지원 API 방식과 현재  API 방식 등 헷갈림
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId("")
                .clientSecret("")
                .tenantId("")
                .build();

        GraphServiceClient graphClient = new GraphServiceClient(clientSecretCredential);
        System.out.println(graphClient.me().get().getId());
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void test() {

    }

    @Override
    public void getMembers() {

    }
}
