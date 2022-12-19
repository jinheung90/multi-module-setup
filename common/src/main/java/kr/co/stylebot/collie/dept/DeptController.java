package kr.co.stylebot.collie.dept;

import kr.co.stylebot.collie.dept.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;
    @GetMapping(value = "/dept")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(deptService.findAll());
    }
}
