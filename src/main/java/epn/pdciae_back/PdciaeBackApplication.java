package epn.pdciae_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "epn")
@EnableMongoRepositories(basePackages = "epn.repositories")
public class PdciaeBackApplication {
	private static final String[] MONGO_URI_ENV_KEYS = {
		"SPRING_DATA_MONGODB_URI",
		"MONGODB_URI",
		"MONGO_URI",
		"MONGODB_URL",
		"DATABASE_URL"
	};

	public static void main(String[] args) {
		configureMongoUriFromEnvironment();
		SpringApplication.run(PdciaeBackApplication.class, args);
	}

	private static void configureMongoUriFromEnvironment() {
		for (String key : MONGO_URI_ENV_KEYS) {
			String value = System.getenv(key);
			if (value != null && !value.isBlank()) {
				System.setProperty("spring.data.mongodb.uri", value.trim());
				return;
			}
		}

		throw new IllegalStateException(
			"MongoDB URI no configurada. Define una variable de entorno: " + String.join(", ", MONGO_URI_ENV_KEYS)
		);
	}

}
