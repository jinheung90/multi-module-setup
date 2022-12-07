package com.example.demo.dept;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;
    @RequestMapping(value = "/dept")
    public String findAll(Model model) {
        model.addAttribute("test", "test");
        model.addAttribute("test2", deptService.findAll());
        return "test3";
    }
    @PostMapping(value = "/dept")
    public String post(@ModelAttribute("deptDto") DeptDTO deptDto) {

        deptService.insert(
                ((long) new Random().nextInt(100)),
                deptDto.getName(),
                deptDto.getLocate()
        );

        return "test3";
    }
    @RequestMapping(value = "/dept/test")
    public String get(Model model) {
        model.addAttribute("deptDto",new DeptDTO()); //빈 오브젝트를 뷰에 넘겨준다.
        return "test4";
    }
}
