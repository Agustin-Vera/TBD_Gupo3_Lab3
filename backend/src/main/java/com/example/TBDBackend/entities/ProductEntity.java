package com.example.TBDBackend.entities;

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
public class ProductEntity {
    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private int stock;

    private String state;

    @DocumentReference(lazy = true)
    private CategoryEntity category;

    @JsonProperty("order_details")
    @JsonIgnore
    @DocumentReference(lazy = true)
    private List<OrderDetailEntity> orderDetails;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}
