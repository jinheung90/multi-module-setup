package kr.co.stylebot.collie.dept;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptDAO deptDAO;
    public List<DeptDTO> findAll() {
        return deptDAO.findAll();
    }
}