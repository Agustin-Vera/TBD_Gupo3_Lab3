package com.example.TBDBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDTO {
    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    private String state;

    @JsonProperty("distributor_id")
    private String distributorId;

    private double total;

    @JsonProperty("shipping_date")
    private Date shippingDate;

    @JsonProperty("delivery_location")
    private String deliveryLocation;

    @JsonProperty("client_id")
    private String clientId;
}
