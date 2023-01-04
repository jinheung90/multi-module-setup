package kr.co.stylebot.collie.dept;

import kr.co.stylebot.collie.product.ProductDAO;
import kr.co.stylebot.collie.product.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class DeptService {
    private final DeptDAO deptDAO;

    @Cacheable(value = "depts")
    public List<DeptDTO> findAll() {
        return deptDAO.findAll();
    }


    @Transactional(transactionManager = "deptTransactionManager") // multidatasoure 일때 트랜잭션 명시해줘야한다
    public void save1() {
        deptDAO.insert(new Dept(1L,"b", "c"));
        Long.valueOf("awesryt");
        deptDAO.insert(new Dept(5L,"b", "c"));
    }


    public void save2() {
        deptDAO.insert(new Dept(2L,"b", "c"));
        Long.valueOf("awesryt");
        deptDAO.insert(new Dept(3L,"b", "c"));
    }
}