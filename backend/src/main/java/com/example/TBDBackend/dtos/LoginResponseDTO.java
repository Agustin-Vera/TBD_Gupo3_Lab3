package com.example.TBDBackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;

    private String clientId;
}
