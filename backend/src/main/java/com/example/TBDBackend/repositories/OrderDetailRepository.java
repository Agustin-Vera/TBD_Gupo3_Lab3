package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {
}
