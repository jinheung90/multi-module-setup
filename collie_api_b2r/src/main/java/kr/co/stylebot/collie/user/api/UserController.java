package kr.co.stylebot.collie.user.api;

//import kr.co.stylebot.collie.user.service.UserService;
import kr.co.stylebot.collie.dept.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final DeptService deptService;

    @RequestMapping(value = "/dept2")
    public String findAll(Model model) {
//
//        model.addAttribute("licenseService", deptService);
        return "test3";
    }

}
