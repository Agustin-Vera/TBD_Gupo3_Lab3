package com.example.TBDBackend.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

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
}
