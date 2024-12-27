package com.example.TBDBackend.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")

@Data
@Builder
public class ClientEntity {
    @Id
    private String id;

    private String name;

    private String address;

    private String email;

    private String password;

    private String phone;
}
