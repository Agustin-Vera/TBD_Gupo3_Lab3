package com.example.TBDBackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDTO {

    private LocalDateTime order_date;
    private String state;
    private String client_id;
    private String distributor_id;
    private double total;
    private Date shipping_date;
    private String delivery_location;
}
