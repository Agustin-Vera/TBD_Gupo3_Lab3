package com.example.TBDBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDetailDTO {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("product_id")
    private String productId;

    private int quantity;

    @JsonProperty("unit_price")
    private double unitPrice;
}
