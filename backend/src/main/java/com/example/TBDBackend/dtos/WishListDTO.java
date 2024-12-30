package com.example.TBDBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WishListDTO {

    private String id;

    @JsonProperty("user")
    private String userId;

    @JsonProperty("products")
    private List<String> productsId;
}
