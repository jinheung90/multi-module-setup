package jinheung.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class JinheungUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinheungUserApplication.class, args);
	}

}
