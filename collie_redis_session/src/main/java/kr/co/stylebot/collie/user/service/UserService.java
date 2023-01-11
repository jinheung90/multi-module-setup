package kr.co.stylebot.collie.user.service;

import kr.co.stylebot.collie.auth.SessionUserDetail;
import kr.co.stylebot.collie.user.domain.Authority;
import kr.co.stylebot.collie.user.domain.User;
import kr.co.stylebot.collie.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Transactional
    public SessionUserDetail usernamePasswordLogin(String name, String password) {
        User user = verifyNameAndPassword(name, password);
        SessionUserDetail sessionUserDetail = toSessionUserDetail(user);
        return sessionUserDetail;
    }

    @Transactional
    public SessionUserDetail toSessionUserDetail(User user) {

        return new SessionUserDetail(
                user.getId(),
                " ",
                user.getDeleted(),
                user.getNickname(),
                user.getAuthorities()
                        .stream()
                        .map(a -> new SimpleGrantedAuthority(a.getAuthorityName()))
                        .collect(Collectors.toList())
        );
    }

    public SecurityContext setAuthenticationContext(SessionUserDetail sessionUserDetail) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(sessionUserDetail, null, sessionUserDetail.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);
        return securityContext;
    }


    public int test1234() {
        return  userRepository.countBy();
    }
//



    @Transactional
    public User verifyNameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("not exists username")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("password not match");
        }
        return user;
    }

    @Transactional
    public User saveUser(String username, String password) {
        User user = User.builder()
                .authorities(new ArrayList<>() {{
                    add(new Authority("ROLE_USER"));
                }})
                .nickname("b")
                .password(passwordEncoder.encode(password))
                .username(username)
                .phoneNum("").build();
        userRepository.save(user);
        return user;
    }
}
