package jinheung.project.user.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "users")
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String nickname;

    public static UserProfile of(Long userId, String nickname) {
        return UserProfile.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}
