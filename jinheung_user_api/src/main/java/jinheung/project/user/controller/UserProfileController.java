package jinheung.project.user.controller;

import jinheung.project.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserProfileController {
    private final UserProfileService userProfileService;
}
