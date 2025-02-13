### 사용 방법

종속성 추가
```xml
<dependency>
    <groupId>com.unipost.unidocu-library</groupId>
    <artifactId>Integrated-External-Messenger</artifactId>
    <version>1.0.0-alpha</version>
</dependency>
```


테스트 버전이므로 sendMessage의 채널은 유니 운영 웹 일반 채널로 고정되어있음

```
String token = "bot token";
PlatformMessengerFactory factory = new PlatformMessengerFactory();
PlatformMessenger slack = factory.create(PlatformMessengerType.SLACK, token);
slack.sendMessage(message);
```