package com.example.TBDBackend.dtos;

import lombok.Data;

@Data
public class AddressRequest {
    private String streetAddress;
    private double latitude;
    private double longitude;
}
