package com.example.TBDBackend.repositories;

import com.example.TBDBackend.models.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishListRepository extends MongoRepository<WishList, String> {
}
