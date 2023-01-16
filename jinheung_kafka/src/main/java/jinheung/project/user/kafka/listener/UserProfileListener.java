package jinheung.project.user.kafka.listener;

import jinheung.project.user.dto.UserProfileDto;
import jinheung.project.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileListener {

    private final UserProfileService userProfileService;
    private final KafkaTemplate<String, UserProfileDto> kafkaTemplate;

    @KafkaListener(topics = "topic")
    public void userProfileSaveListener(@Payload UserProfileDto userProfileDto) {
        userProfileService.saveUserProfileWhenSignup(userProfileDto.getUserId(), userProfileDto.getNickname());
    }
}
