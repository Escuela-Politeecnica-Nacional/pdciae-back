package epn.repositories;

import epn.schemas.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorRepository extends MongoRepository<Tutor, String> {
}
