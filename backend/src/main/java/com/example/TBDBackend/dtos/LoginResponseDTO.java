package com.example.TBDBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;

    @JsonProperty("client_id")
    private String clientId;
}
