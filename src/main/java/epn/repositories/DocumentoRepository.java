package epn.repositories;

import epn.schemas.Documento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentoRepository extends MongoRepository<Documento, String> {
}
