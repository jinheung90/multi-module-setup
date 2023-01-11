package kr.co.stylebot.collie.dept;


import kr.co.stylebot.collie.dept.DeptService;
import kr.co.stylebot.collie.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;
    private final ProductService productService;
    @GetMapping(value = "/dept")
    public ResponseEntity<?> findAll() {
        System.out.println("t");
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("a", deptService.findAll());
                    put("b", productService.findAll());
                }});
    }
    @GetMapping(value = "/a")
    public ResponseEntity<?> test() {
        System.out.println("t");
        return ResponseEntity.ok("a");
    }

    @GetMapping(value = "/b")
    public ResponseEntity<?> test2() {
        deptService.save1();
        return ResponseEntity.ok("a");
    }
    @GetMapping(value = "/c")
    public ResponseEntity<?> test3() {
        deptService.save2();
        return ResponseEntity.ok("a");
    }
    @PostMapping(value = "/d")
    public ResponseEntity<?> test4(@Valid @RequestBody TestA testA) {
        deptService.save2();
        return ResponseEntity.ok("a");
    }
    @GetMapping(value = "/d")
    public ResponseEntity<?> aseryh() {

        return ResponseEntity.ok("a");
    }
}


