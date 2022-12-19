package kr.co.stylebot.collie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication

public class CollieRedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollieRedisApplication.class, args);
	}
}
