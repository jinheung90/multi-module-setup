package jinheung.project.user.service;

import jinheung.project.user.entity.UserProfile;
import jinheung.project.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfile saveUserProfileWhenSignup( Long userId,String nickname) {
        return userProfileRepository.save(UserProfile.of(userId,nickname));
    }
}
