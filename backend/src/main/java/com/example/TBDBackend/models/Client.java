package com.example.TBDBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "clients")

@Data
@Builder
public class Client {
    @Id
    private String id;

    private String name;

    private Address address;

    private String email;

    private String password;

    private String phone;

    private List<Address> addresses;

    @DocumentReference(lazy = false)
    private WishList wishlist;

    @JsonIgnore
    private Object target;

    @JsonIgnore
    private Object source;
}