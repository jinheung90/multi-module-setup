package jinheung.project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto implements Serializable {
    private String nickname;
    private Long userId;
}
