package com.example.TBDBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products")

@Data
@Builder
public class Product {
    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private int stock;

    private String state;

    @DocumentReference(lazy = true)
    private Category category;

    @JsonProperty("order_details")
    @JsonIgnore
    @DocumentReference(lazy = true)
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}
