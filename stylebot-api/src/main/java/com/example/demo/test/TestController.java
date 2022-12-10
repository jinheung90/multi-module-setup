package com.example.demo.test;


import com.example.demo.dept.Dept;
import com.example.demo.dept.DeptService;
import com.example.demo.shop.domain.Shop;
import com.example.demo.shop.service.ShopService;
import com.example.demo.user.domain.User;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final DeptService deptService;
    private final UserService userService;
    private final ShopService shopService;
    @GetMapping(value = "/dept")
    public ResponseEntity<List<Dept>> findAll() {
        return ResponseEntity.ok(deptService.findAll());
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/shop")
    public ResponseEntity<List<Shop>> findAllShop() {
        return ResponseEntity.ok(shopService.findAll());
    }

}
