package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
