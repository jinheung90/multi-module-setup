package kr.co.stylebot.collie.base.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final RedisTemplate<String, String> redisTemplate;
    public void saveTest(String a, String b) {
        redisTemplate.opsForValue().set(a, b);
    }

    public String get(String a) {
        return redisTemplate.opsForValue().get(a);
    }
}
