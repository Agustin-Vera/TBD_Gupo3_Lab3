package com.example.TBDBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "order_details")

@Data
@Builder
public class OrderDetail {
    @Id
    private String id;

    private int quantity;

    @JsonProperty("unit_price")
    private double unitPrice;

    @DocumentReference(lazy = true)
    private Order order;

    @DocumentReference(lazy = true)
    private Product product;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}
