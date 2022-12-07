package com.example.demo.dept;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDAO {

    public List<Dept> findAll();


    public void insert(
            @Param(value = "deptNo") Long deptNo,
            @Param(value = "dName") String dName,
            @Param(value = "locate") String locate);

    @Update("UPDATE DEPT SET DNAME = #{dName}, LOC = #{locate} WHERE DEPTNO = #{deptNo}")
    public void update(@Param(value = "deptNo") Long deptNo, @Param(value = "dName") String dName, @Param(value = "locate") String locate);

    @Delete("DELETE FROM DEPT WHERE DEPTNO = #{deptNo}")
    public void deleteById(@Param(value = "deptNo") Long deptNo);


}
