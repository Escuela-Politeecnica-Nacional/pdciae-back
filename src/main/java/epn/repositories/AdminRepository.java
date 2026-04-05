package epn.repositories;

import epn.schemas.Admin;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

// findyby finds delete create
public interface AdminRepository extends MongoRepository<Admin, String> {
    boolean existsByEmail(String email);
    Optional<Admin> findByEmail(String email);
}
