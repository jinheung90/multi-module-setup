package kr.co.stylebot.collie.dept;



import kr.co.stylebot.collie.dept.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
