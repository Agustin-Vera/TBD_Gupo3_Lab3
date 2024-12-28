package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.ClientEntity;
import com.example.TBDBackend.entities.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogEntity, String> {
}
