package demo.dept;


import com.example.demo.dept.DeptDTO;
import com.example.demo.dept.DeptService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;


}
