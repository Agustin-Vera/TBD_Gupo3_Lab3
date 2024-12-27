package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
    Optional<ClientEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
