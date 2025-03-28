### 사용 방법

종속성 추가
```xml
<dependency>
    <groupId>com.unipost.unidocu-library</groupId>
    <artifactId>Integrated-External-Messenger</artifactId>
    <version>1.0.0-alpha</version>
</dependency>
```

메신저 초기화
```java
String token = "bot token";
PlatformMessengerFactory factory = new PlatformMessengerFactory();
PlatformMessenger slack = factory.create(PlatformMessengerType.SLACK, token);
```

일반 문자열 메시지 전송

```java
SlackPlatformMessengerTarget messageTarget = new SlackPlatformMessengerTarget.Builder()
        .channelId("channelId") // channelId or email, email is optional
        .email("email")
        .message("안녕하세요")
        .build();
slack.sendMessage(target);
```

blocks kit 메시지 전송(카드 타입)

슬랙에서 제공해주는 blocks kit 사이트 https://api.slack.com/tools/block-kit-builder 를 참고해서 작성

List 또는 blocks kit json 문자열을 사용하여 메시지 전송

blocks kit 사이트 내 보내고 싶은 데이터를 작성 후 json, mustache 등 템플릿엔진 등을 사용하여

전송하면 조금 더 간편하고, 유지 보수가 쉽게 작성 가능함

예시 card type json
```json
[
    {
        "type": "section",
        "text": {
            "type": "mrkdwn",
            "text": "Hello World"
        }
    },
    {
        "type": "divider"
    },
    {
        "type": "actions",
        "elements": [
            {
                "type": "button",
                "text": {
                    "type": "plain_text",
                    "emoji": true,
                    "text": "button"
                },
                "value": "click"
            }
        ]
    }
]
```

1. List 사용
```java
List<Map<String, Object>> blocksList = new ArrayList<>();

Map<String, Object> section = new HashMap<>();
section.put("type", "section");
Map<String, String> text = new HashMap<>();
text.put("type", "mrkdwn");
text.put("text", "Hello World");
section.put("text", text);
blocksList.add(section);

Map<String, Object> divider = new HashMap<>();
divider.put("type", "divider");
blocksList.add(divider);

Map<String, Object> actions = new HashMap<>();
actions.put("type", "actions");
List<Map<String, Object>> elements = new ArrayList<>();

Map<String, Object> button1 = new HashMap<>();
button1.put("type", "button");
Map<String, Object> text1 = new HashMap<>();
text1.put("type", "plain_text");
text1.put("emoji", true);
text1.put("text", "test1");
button1.put("text", text1);
button1.put("value", "v1");
elements.add(button1);

actions.put("elements", elements);
blocksList.add(actions);

SlackPlatformMessengerTarget messageTarget = new SlackPlatformMessengerTarget.Builder()
        .channelId("channelId") // channelId or email, email is optional
        .email("email")
        .message(blocksList)
        .build();
slack.sendCardMessage(target);
```

2. json 문자열 사용
```java
String message = "[{\"type\":\"section\",\"text\":{\"type\":\"mrkdwn\",\"text\":\"Hello World\"}},{\"type\": \"divider\"},{\"type\":\"actions\",\"elements\": [{\"type\": \"button\",\"text\": {\"type\": \"plain_text\",\"emoji\": true,\"text\": \"button\"},\"value\": \"click\"}]}]"
SlackPlatformMessengerTarget messageCardTarget = new SlackPlatformMessengerTarget.Builder()
        .channelId("channelId") // channelId or email, email is optional
        .email("email")
        .message(message)
        .build();
slack.sendCardMessage(target);
```