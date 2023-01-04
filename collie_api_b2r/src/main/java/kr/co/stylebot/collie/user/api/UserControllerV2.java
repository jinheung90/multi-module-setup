package kr.co.stylebot.collie.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
//import kr.co.stylebot.collie.auth.SecuritySupport;
//import kr.co.stylebot.collie.auth.SessionUserDetail;
//import kr.co.stylebot.collie.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserControllerV2 {

//    private final UserService userService;
//
//    @GetMapping("/test")
//    public ResponseEntity<?> save() {
//        userService.saveUser("asdfasdf","awetawegaweg");
//        return ResponseEntity.ok("");
//    }
//
//    @GetMapping("/test2")
//    public ResponseEntity<?> usernamePasswordLogin() {
//        SessionUserDetail user = userService.usernamePasswordLogin("asdfasdf","awetawegaweg");
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(
//                        user
//                        , null, null);
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        return ResponseEntity.ok("");
//    }
//
//    @GetMapping("/test3")
//    public ResponseEntity<?> test3(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok("");
//    }
//
//    @GetMapping("/test4")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<?> test4(HttpSession session) {
//
//        return ResponseEntity.ok(SecuritySupport.getUserDetail());
//    }
}
