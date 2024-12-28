package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.OrderDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailRepository extends MongoRepository<OrderDetailEntity, String> {
}
