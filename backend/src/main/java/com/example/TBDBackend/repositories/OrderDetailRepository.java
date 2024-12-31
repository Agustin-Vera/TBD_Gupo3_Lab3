package com.example.TBDBackend.repositories;

import com.example.TBDBackend.models.Order;
import com.example.TBDBackend.models.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {

    List<OrderDetail> findByOrder_Id(String orderId);
}
