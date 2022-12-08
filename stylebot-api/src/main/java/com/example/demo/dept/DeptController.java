package com.example.demo.dept;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;
    @GetMapping(value = "/dept")
    public ResponseEntity<List<Dept>> findAll() {
        return ResponseEntity.ok(deptService.findAll());
    }
    @PostMapping(value = "/dept")
    public  ResponseEntity<String> post(@RequestBody DeptDTO deptDTO) {

        deptService.insert(
                deptDTO
        );

        return ResponseEntity.ok("success");
    }

}
