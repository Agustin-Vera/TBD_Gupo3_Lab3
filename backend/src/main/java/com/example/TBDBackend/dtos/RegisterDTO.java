package com.example.TBDBackend.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;

    private String address;

    private String email;

    private String password;

    private String phone;

    private String streetAddress;

    private double latitude;

    private double longitude;
}
