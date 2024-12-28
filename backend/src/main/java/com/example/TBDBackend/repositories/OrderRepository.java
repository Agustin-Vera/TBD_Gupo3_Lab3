package com.example.TBDBackend.repositories;

import com.example.TBDBackend.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
