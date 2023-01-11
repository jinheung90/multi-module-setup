package jinheung.project.auth;


import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuritySupport {

    public static Long getUserId() {
        Authentication authentication = isAuthentication();
        return Long.valueOf(authentication.getName());
    }

    public static SessionUserDetail getUserDetail() {
        Authentication authentication = isAuthentication();
        Object detail = authentication.getPrincipal();
        System.out.println(detail);
        return new Gson().fromJson(detail.toString(), SessionUserDetail.class);
    }

    private static Authentication isAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()) {
            throw new RuntimeException("no authentication");
        }

        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"))) {
            throw new RuntimeException("anonymous user");
        }
        return authentication;
    }
}
