package com.backend.TBD_Backend.repositories;

import com.backend.TBD_Backend.models.ClientEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, ObjectId> {
}
