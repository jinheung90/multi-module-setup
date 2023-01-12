package com.jinheung.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecuritySupport {

    private static final String USER_ID_HEADER_NAME = "x-user-id";
    private static final String USER_AUTHORITIES_HEADER_NAME = "x-user-authorities";
    public Long getUserId() {
        HttpServletRequest req =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String value = req.getHeader(USER_ID_HEADER_NAME);
        if(value.isBlank()) {
            throw new RuntimeException();
        }
        return Long.valueOf(req.getHeader(USER_ID_HEADER_NAME));
    }
    public List<String> getAuthorities() {
        HttpServletRequest req =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String value = req.getHeader(USER_AUTHORITIES_HEADER_NAME);
        if(value.isBlank()) {
            throw new RuntimeException();
        }
        return List.of(value.split(","));
    }
}
