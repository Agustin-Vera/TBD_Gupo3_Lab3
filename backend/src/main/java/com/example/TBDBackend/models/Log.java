package com.example.TBDBackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Log {
    @Id
    private String id;

    @JsonProperty("original_price")
    private BigDecimal originalPrice;

    @JsonProperty("new_price")
    private BigDecimal newPrice;

    @JsonProperty("update_date")
    private Date updateDate;

    @DocumentReference(lazy = true)
    private Client client;
}
