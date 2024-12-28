package com.example.TBDBackend.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("categories")

@Data
@Builder
public class CategoryEntity {

    @Id
    private String id;

    private String name;
}
