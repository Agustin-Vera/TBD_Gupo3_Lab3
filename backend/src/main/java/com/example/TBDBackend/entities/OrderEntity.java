package com.example.TBDBackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")

@Data
@Builder
public class OrderEntity {
    @Id
    private String id;

    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    private String state;

    @JsonProperty("distributor_id")
    private String distributorId;

    private double total;

    @JsonProperty("shipping_date")
    private Date shippingDate;

    @JsonProperty("delivery_location")
    private String deliveryLocation;

    @JsonIgnore
    @DocumentReference(lazy = true)
    private ClientEntity client;

    @JsonProperty("order_details")
    @JsonIgnore
    @DocumentReference(lazy = true)
    private List<OrderDetailEntity> orderDetails;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}
