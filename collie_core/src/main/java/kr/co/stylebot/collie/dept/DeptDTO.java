package kr.co.stylebot.collie.dept;

import lombok.Data;

import java.io.Serializable;

@Data

public class DeptDTO implements Serializable {
    private Long DEPTNO;
    private String DNAME;
    private String LOC;
}
