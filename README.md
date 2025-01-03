### 사용 방법

테스트 버전이므로 sendMessage의 채널은 유니 운영 웹 일반 채널로 고정되어있음

```java
String token = "bot token";
PlatformMessengerFactory factory = new PlatformMessengerFactory();
PlatformMessenger slack = factory.create(PlatformMessengerType.SLACK, token);
slack.sendMessage(message);
```