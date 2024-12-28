package com.example.TBDBackend.entities;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "orders")

@Data
@Builder
public class OrderEntity {

    @Id
    private String id;

    private LocalDateTime order_date;
    private String state;
    private String client_id;
    private String distributor_id;
    private double total;
    private Date shipping_date;
    private String delivery_location;

}
