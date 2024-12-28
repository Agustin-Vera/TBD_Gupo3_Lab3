package com.example.TBDBackend.repositories;

import com.example.TBDBackend.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
