package com.example.TBDBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document("categories")

@Data
@Builder
public class Category {
    @Id
    private String id;

    private String name;

    @JsonIgnore
    @DocumentReference(lazy = true)
    private List<Product> products;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}
