package kr.co.stylebot.collie.dept;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dept {
    private Long deptNo;
    private String dName;
    private String locate;
}
