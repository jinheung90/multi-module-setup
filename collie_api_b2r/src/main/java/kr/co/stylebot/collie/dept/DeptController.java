package kr.co.stylebot.collie.dept;


import kr.co.stylebot.collie.dept.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;
    @GetMapping(value = "/dept")
    public ResponseEntity<?> findAll() {
        System.out.println("t");
        return ResponseEntity.ok(deptService.findAll());
    }
    @GetMapping(value = "/a")
    public ResponseEntity<?> test() {
        System.out.println("t");
        return ResponseEntity.ok("a");
    }

}
