package jinheung.project.user.kafka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserTopics {

    USER_PROFILE_SAVE("user_profileSave");

    private String value;


}
