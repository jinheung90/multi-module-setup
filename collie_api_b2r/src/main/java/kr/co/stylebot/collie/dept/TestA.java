package kr.co.stylebot.collie.dept;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TestA {

    @NotNull
    private TestB testB;
    @NotNull
    private boolean testc;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class TestB {

        @NotNull
        private String t;
    }
}

