package kr.co.stylebot.collie.dept;

import kr.co.stylebot.collie.base.service.TestService;
import kr.co.stylebot.collie.dept.DeptService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;
    private final TestService testService;
    @GetMapping(value = "/dept")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(deptService.findAll());
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        testService.test();
        return ResponseEntity.ok("deptService.findAll()");
    }

    @GetMapping(value = "/test2")
    public ResponseEntity<?> test2(
            HttpServletRequest request,
            HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("test", "b");
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("b", "c");
        session.setAttribute("c", "d");
        System.out.println("testasdf");
        return ResponseEntity.ok("b");
    }
}
