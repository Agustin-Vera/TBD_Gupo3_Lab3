package com.example.TBDBackend.repositories;

import com.example.TBDBackend.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
