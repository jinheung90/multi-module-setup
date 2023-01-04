package kr.co.stylebot.collie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CollieCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollieCoreApplication.class, args);
	}

}
