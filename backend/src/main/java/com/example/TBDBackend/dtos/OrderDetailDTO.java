package com.example.TBDBackend.dtos;

import lombok.Data;

@Data
public class OrderDetailDTO {

    private String order_id;
    private String product_id;
    private int quantity;
    private double unit_price;

}
