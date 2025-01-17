package com.example.TBDBackend.repositories;

import com.example.TBDBackend.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);

    Boolean existsByEmail(String email);
}
