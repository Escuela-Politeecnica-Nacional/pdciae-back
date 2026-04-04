package epn.pdciae_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "epn")
@EnableMongoRepositories(basePackages = "epn.repositories")
public class PdciaeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdciaeBackApplication.class, args);
	}

}
