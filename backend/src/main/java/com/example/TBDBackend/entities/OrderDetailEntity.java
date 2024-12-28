package com.example.TBDBackend.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_details")

@Data
@Builder
public class OrderDetailEntity {

    @Id
    private String id;

    private String order_id;
    private String product_id;
    private int quantity;
    private double unit_price;
}
