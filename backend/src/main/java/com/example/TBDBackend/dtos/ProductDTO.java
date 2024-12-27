package com.example.TBDBackend.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;

    private String description;

    private BigDecimal price;

    private int stock;

    private String state;
}
