package com.backend.TBD_Backend.dtos;

import org.bson.types.ObjectId;

public class LoginResponseDTO {
    private String token;
    private ObjectId userId;

    public LoginResponseDTO(String token, ObjectId userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}