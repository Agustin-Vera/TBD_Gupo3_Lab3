package com.example.TBDBackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "wish_list")

@Data
@Builder
public class WishList {
    @Id
    private String id;

    @JsonIgnore
    @DocumentReference(lazy = true)
    private Client client;

    @DocumentReference(lazy = true)
    private List<Product> products;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;

}
