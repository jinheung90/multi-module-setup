package com.example.demo.dept;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptDAO deptDAO;

    public void insert(DeptDTO deptDTO) {
        deptDAO.insert(Long.valueOf(new Random().nextInt(10000)),deptDTO.getName(),deptDTO.getLocate());
    }

    public void update(Long id, String name, String locate) {
        deptDAO.update(id, name, locate);
    }

    public List<Dept> findAll() {
        return deptDAO.findAll();
    }

    public void delete(Long id) {
        deptDAO.deleteById(id);
    }
}
