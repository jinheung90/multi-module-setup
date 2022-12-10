package com.example.demo.dept;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDAO {

    List<Dept> findAll();


    void insert(
            @Param(value = "deptNo") Long deptNo,
            @Param(value = "dName") String dName,
            @Param(value = "locate") String locate);


    void update(@Param(value = "deptNo") Long deptNo, @Param(value = "dName") String dName, @Param(value = "locate") String locate);


    void deleteById(@Param(value = "deptNo") Long deptNo);


}
